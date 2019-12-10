package com.ens.service.content;

import com.ens.config.content.ContentHostProperties;
import com.ens.config.content.NginxProperties;
import com.ens.domain.FileProperties;
import com.ens.domain.entity.content.ContentInfo;
import com.ens.domain.request.ContentRequest;
import com.ens.domain.response.ContentMetadata;
import com.ens.domain.response.ContentResponse;
import com.ens.exception.FileStorageException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ContentBusinessService {

    @Autowired
    private ContentService contentService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private NginxProperties nginxProperties;

    @Autowired
    private ContentHostProperties hostProperties;

    @Autowired
    private ContentValidationService validationService;

    @Value("${content.metadata.prefix}")
    private String contentMetadataPrefix;

    /**
     *
     */
    public ContentResponse uploadFile(ContentRequest request) {

        //TODO Need to implement file validation based on tenant
        final String fileName = getFileName(request);
        final String filePath = fileStorageService
                .storeFile(request.getFile(), getFilePath(request), fileName);
        final FileProperties fileProperties = fileStorageService
                .getFileProperties(request.getFile(),
                        Paths.get(fileStorageService.getFileStorageLocation().toString(), filePath),
                        fileName);

        ContentInfo contentInfo = new ContentInfo();
        contentInfo.setContentLength(fileProperties.getSize());
        contentInfo.setContentType(fileProperties.getType());
        contentInfo.setFileExtension(fileProperties.getExtension());
        contentInfo.setFileName(fileProperties.getName());
        contentInfo.setMd5CheckSum(fileStorageService.getMD5CheckSum(request.getFile()));
        contentInfo.setFilePath(filePath);
        contentInfo.setTenantId(request.getTenantId());
        contentInfo.setUserId(request.getUserId());
        contentInfo.setServiceId(request.getServiceId());
//        contentInfo.setCreatedBy(request.getUserId());
        contentInfo.setMetaData(getMetadata(request.getHttpServletRequest()));
        contentInfo.getMetaData().put("dimensions", fileProperties.getDimension());
        contentInfo.getMetaData().put("duration", fileProperties.getVideoDuration());

        contentInfo = contentService.save(contentInfo);

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(hostProperties.getScheme()).host(hostProperties.getHost())
                .port(hostProperties.getPort())
                .path(hostProperties.getPath())
                .path("/")
                .path(contentInfo.getId().toString())
                .build().encode();

        UriComponents downloadUrl = UriComponentsBuilder.newInstance()
                .scheme(hostProperties.getScheme()).host(hostProperties.getHost())
                .port(hostProperties.getPort())
                .path(hostProperties.getPath())
                .path("/download/")
                .path(contentInfo.getId().toString())
                .build().encode();

        log.info("### Content URL : {}", uriComponents.toUriString());

        return new ContentResponse(contentInfo.getId(), HttpStatus.OK, "Success",
                uriComponents.toUriString(), downloadUrl.toUriString(),
                getContentMetadata(contentInfo.getId()));
    }

    /**
     *
     */
    public Resource getContent(UUID id) {

        ContentInfo contentInfo = validationService.validateAndGet(id);

        return fileStorageService
                .loadFileAsResource(contentInfo.getFilePath(), contentInfo.getFileName());
    }

    /**
     *
     */
    public String getContentUri(UUID id) {

        ContentInfo contentInfo = validationService.validateAndGet(id);

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme(nginxProperties.getScheme()).host(nginxProperties.getHost())
                .port(nginxProperties.getPort())
                .path(nginxProperties.getPath())
                .path(contentInfo.getFilePath())
                .path("/")
                .path(contentInfo.getFileName())
                .build().encode();

        log.info("### NGINX Content URL : {}", uriComponents.toUriString());

        return uriComponents.toUriString();
    }

    /**
     *
     */
    public ContentMetadata getContentMetadata(UUID id) {

        ContentInfo contentInfo = validationService.validateAndGet(id);

        ContentMetadata metadata = new ContentMetadata();

        metadata.setContentType(contentInfo.getContentType());
//        metadata.setCreatedOn(contentInfo.getCreatedDate().getTime());
        metadata.setLength(contentInfo.getContentLength());
        metadata.setName(contentInfo.getFileName());
        metadata.setServiceId(contentInfo.getServiceId());
        metadata.setTenantId(contentInfo.getTenantId());
        metadata.setUserId(contentInfo.getUserId());
        metadata.setMetadata(contentInfo.getMetaData());

        return metadata;
    }

    /**
     * @param id content id
     */
    public void delete(UUID id) throws IOException {

        ContentInfo contentInfo = validationService.validateAndGet(id);

        fileStorageService.delete(Paths.get(fileStorageService.getFileStorageLocation().toString(),
                contentInfo.getFilePath(), contentInfo.getFileName()));

        contentService.delete(id);

    }

    /**
     *
     */
    private Map<String, Object> getMetadata(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String key = (String) headerNames.nextElement();
            if (key.startsWith(contentMetadataPrefix)) {
                map.put(key.replaceFirst(contentMetadataPrefix, ""), request.getHeader(key));
            }
        }
        return map;
    }

    public Page<ContentInfo> findAll(Pageable pageable) {
        return contentService.findAll(pageable);
    }

    private String getFileName(ContentRequest request) {
        return new StringBuilder().append(
                StringUtils.isEmpty(request.getFileName()) ? UUID.randomUUID().toString()
                        : request.getFileName()).append(".")
                .append(FilenameUtils.getExtension(request.getFile().getOriginalFilename()))
                .toString();
    }

    private Path getFilePath(ContentRequest request) {
        return StringUtils.isEmpty(request.getFilePath()) ? Paths
                .get(request.getTenantId(), request.getServiceId(), request.getUserId())
                : Paths.get(request.getFilePath());
    }

    public List<String> getFileNames(String userId, String filePath) {
        return contentService.getFileNames(userId, fileStorageService.getFormattedPath(filePath));
    }

    public void delete(String filePath, String userId) throws IOException {

        Path pathToDelete = Paths
                .get(fileStorageService.getFileStorageLocation().toString(), filePath);

        if (pathToDelete.toFile().isDirectory()) {
            throw new FileStorageException(
                    "You can delete only specific file, but you have given dir : " + filePath);
        }

        final String fileName = pathToDelete.toFile().getName();
        final String actualPath = fileStorageService
                .getFormattedPath(Paths.get(filePath).getParent().toString());
        boolean allowedToDelete = contentService.isAllowedToDelete(userId, actualPath, fileName);

        if (!allowedToDelete) {
            throw new FileStorageException(
                    "File not found (or) Not authorized to delete this file");
        }

        contentService.deleteContent(userId, actualPath, fileName);
        fileStorageService.delete(pathToDelete);

    }
}
