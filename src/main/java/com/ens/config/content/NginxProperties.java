package com.ens.config.content;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "nginx")
@Setter
@Getter
public class NginxProperties {

    private String scheme;
    private String host;
    private String path;
    private int port;

}
