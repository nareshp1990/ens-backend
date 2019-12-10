package com.ens.service.content;

import static com.ens.util.ContentUtils.getImageDimension;
import static com.ens.util.ContentUtils.getVideoDuration;

import com.ens.config.content.FileStorageProperties;
import com.ens.domain.FileProperties;
import com.ens.exception.ContentNotFoundException;
import com.ens.exception.FileStorageException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileStorageService {

    private final Path fileStorageLocation;

    /**
     *
     */
    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            log.error("{}", ex);
            throw new FileStorageException(
                    "Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     *
     */
    public String storeFile(MultipartFile file, Path absolutePath, String fileName) {

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException(
                        "Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = Paths
                    .get(this.fileStorageLocation.toString(), absolutePath.toString());

            try {
                Files.createDirectories(targetLocation);
            } catch (Exception ex) {
                log.error("{}", ex);
                throw new FileStorageException(
                        "Could not create the directory where the uploaded files will be stored.",
                        ex);
            }
//            if (targetLocation.resolve(fileName).toFile().exists()) {
//                throw new FileStorageException("Could not store file " + fileName + " . File already exists");
//            }
            Files.copy(file.getInputStream(), targetLocation.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return getFormattedPath(absolutePath.toString());
        } catch (IOException ex) {
            log.error("{}", ex);
            throw new FileStorageException(
                    "Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     *
     */
    public FileProperties getFileProperties(MultipartFile file, Path filePath, String fileName) {

        if (file == null) {
            throw new RuntimeException("Multipart file is null");
        }

        FileProperties properties = new FileProperties();
        properties.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        properties.setName(fileName);
        properties.setSize(file.getSize());
        properties.setType(file.getContentType());
        if (file.getContentType().startsWith("image")) {
            properties.setDimension(getImageDimension(filePath.resolve(fileName).toString()));
        } else if (file.getContentType().startsWith("video")) {
            properties.setVideoDuration(getVideoDuration(filePath.resolve(fileName).toString()));
        }
        return properties;
    }

    /**
     *
     */
    public Resource loadFileAsResource(String filePath, String fileName) {
        try {

            //Path path = this.fileStorageLocation.resolve(Paths.get(filePath)).resolve(fileName).normalize();
            Path path = Paths.get(this.fileStorageLocation.toString(), filePath, fileName)
                    .normalize();
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ContentNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            log.error("{}", ex);
            throw new ContentNotFoundException("File not found " + fileName, ex);
        }
    }

    /**
     * @param file multipart file
     */
    public String getMD5CheckSum(MultipartFile file) {

        if (file == null) {
            throw new RuntimeException("Multipart file is null");
        }

        try {
            return DigestUtils.md5Hex(file.getInputStream());
        } catch (IOException ex) {
            log.error("{}", ex);
            return null;
        }
    }

    /**
     * @param path file to be deleted
     */
    public void delete(Path path) throws IOException {
        FileUtils.forceDelete(path.toFile());
    }

    /**
     * @return the Path
     */
    public Path getFileStorageLocation() {
        return fileStorageLocation;
    }

    public Set<String> listFiles(String filePath) throws IOException {
        return Files.walk(Paths.get(this.fileStorageLocation.toString(), filePath))
                .filter(Files::isRegularFile)
                .map(x -> x.getFileName().toString()).collect(Collectors.toSet());
    }

    public String getFormattedPath(String path) {
        return new StringBuilder()
                .append(!path.startsWith("/") ? "/" : "")
                .append(path)
                .append(!path.endsWith("/") ? "/" : "").toString();
    }

}
