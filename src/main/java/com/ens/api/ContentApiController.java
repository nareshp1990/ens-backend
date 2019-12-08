package com.ens.api;

import com.ens.domain.request.ContentRequest;
import com.ens.domain.response.ContentMetadata;
import com.ens.domain.response.ContentResponse;
import com.ens.service.ContentBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "content service", description = "The content service API", tags = {"content"})
@Slf4j
@RestController
@RequestMapping("/v1")
public class ContentApiController {

    @Autowired
    private ContentBusinessService contentBusinessService;

    @ApiOperation(value = "upload single content file", tags = {
            "content"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "content response", response = ContentResponse.class)})
    @PostMapping(value = "/upload/{serviceId}/{tenantId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadContent(@ApiParam(value = "id of a tenant",
            required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "id of a user",
                    required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "sender application name",
                    required = true) @PathVariable("serviceId") String serviceId,
            @ApiParam(value = "content file",
                    required = true) @RequestParam(value = "file") MultipartFile file,
            @ApiParam(value = "file path",
                    required = false) @RequestParam(value = "filePath", required = false) String filePath,
            @ApiParam(value = "file name without extension",
                    required = false) @RequestParam(value = "fileName", required = false) String fileName,
            HttpServletRequest httpServletRequest) {

        log.info("Given tenantId : {} , userId : {}, senderApp : {}", tenantId, userId, serviceId);

        if (file == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(contentBusinessService.uploadFile(
                new ContentRequest(filePath, fileName, tenantId, userId, serviceId, file,
                        httpServletRequest)));
    }

    @ApiOperation(value = "download content", tags = {"content"})
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getContent(@PathVariable("id") UUID id,
            HttpServletRequest request) throws IOException {
        log.info("Given content id : {}", id);
        // Load file as Resource
        Resource resource = contentBusinessService.getContent(id);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(resource.getFile().length())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(IOUtils.toByteArray(resource.getInputStream()));
    }

    @ApiOperation(value = "View content", tags = {"content"})
    @GetMapping("/{id}")
    public void redirectURL(@PathVariable("id") UUID id, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(contentBusinessService.getContentUri(id));
    }

    @ApiOperation(value = "Get content metadata", tags = {
            "content"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "content metadata", response = ContentMetadata.class)})
    @GetMapping("/{id}/info")
    public ResponseEntity getContentMetadata(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(contentBusinessService.getContentMetadata(id));
    }

    @ApiOperation(value = "Delete content", tags = {"content"})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteContent(@PathVariable("id") UUID id) throws IOException {
        contentBusinessService.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "View all content list", tags = {"content"})
    @GetMapping("/list/{tenantId}")
    public ResponseEntity getContentList(@ApiParam(value = "id of a tenant",
            required = true) @PathVariable("tenantId") String tenantId,
            @PageableDefault(page = 0, size = 100) Pageable pageable) {
        return ResponseEntity.ok(contentBusinessService.findAll(pageable));
    }

    @ApiOperation(value = "upload multiple content files", tags = {
            "content"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "content response", response = ContentResponse[].class)})
    @PostMapping(value = "/upload/multiple/{serviceId}/{tenantId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadMultipleFiles(@ApiParam(value = "id of a tenant",
            required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "id of a user",
                    required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "sender application name",
                    required = true) @PathVariable("serviceId") String serviceId,
            @ApiParam(value = "content files",
                    required = true) @RequestParam(value = "files") MultipartFile[] files,

            @ApiParam(value = "file path",
                    required = false) @RequestParam(value = "filePath") String filePath,

            HttpServletRequest httpServletRequest) {

        //TODO Need to restrict no. of upload files

        log.info("Given tenantId : {} , userId : {}, senderApp : {}", tenantId, userId, serviceId);

        if (files == null || files.length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<ContentResponse> response = Arrays.asList(files)
                .stream().map(file -> contentBusinessService.uploadFile(
                        new ContentRequest(filePath, null, tenantId, userId, serviceId, file,
                                httpServletRequest)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "upload content in specified path", tags = {
            "content"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "content response", response = ContentResponse.class)})
    @PostMapping(value = "/admin/upload/{serviceId}/{tenantId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadAdminContent(@ApiParam(value = "id of a tenant",
            required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "id of a user",
                    required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "sender application name",
                    required = true) @PathVariable("serviceId") String serviceId,
            @ApiParam(value = "content file",
                    required = true) @RequestParam(value = "file") MultipartFile file,
            @ApiParam(value = "file path",
                    required = true) @RequestParam(value = "filePath") String filePath,
            @ApiParam(value = "file name without extension",
                    required = true) @RequestParam(value = "fileName") String fileName,
            HttpServletRequest httpServletRequest) {

        log.info("Given tenantId : {} , userId : {}, senderApp : {}, filePath : {}, fileName : {}",
                tenantId, userId, serviceId, filePath, fileName);

        if (file == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(contentBusinessService.uploadFile(
                new ContentRequest(filePath, fileName, tenantId, userId, serviceId, file,
                        httpServletRequest)));
    }

    @ApiOperation(value = "list content for the specified path", tags = {
            "content"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/list/files/{tenantId}/{userId}")
    public ResponseEntity listContent(
            @ApiParam(value = "id of a tenant", required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "id of a user", required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "file path to view list of file names", required = true) @RequestParam("filePath") String filePath)
            throws IOException {
        if (StringUtils.isEmpty(filePath) || StringUtils.isEmpty(userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(contentBusinessService.getFileNames(userId, filePath));
    }

    @ApiOperation(value = "delete content for the specified path", tags = {
            "content"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping(value = "/delete/{tenantId}/{userId}")
    public ResponseEntity deleteContentByPath(
            @ApiParam(value = "id of a tenant", required = true) @PathVariable("tenantId") String tenantId,
            @ApiParam(value = "id of a user", required = true) @PathVariable("userId") String userId,
            @ApiParam(value = "filename to delete with respective path", required = true) @RequestParam("filePath") String filePath)
            throws IOException {
        if (StringUtils.isEmpty(filePath) || StringUtils.isEmpty(userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        contentBusinessService.delete(filePath, userId);
        return ResponseEntity.ok().build();
    }

}
