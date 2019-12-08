package com.ens.config.content;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "content")
@Setter
@Getter
public class ContentHostProperties {

    private String scheme;
    private String host;
    private String path;
    private int port;

}
