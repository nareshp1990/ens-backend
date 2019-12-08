package com.ens.config.content;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "files")
@Setter
@Getter
public class FileStorageProperties {

    private String uploadDir;

}
