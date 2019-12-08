package com.ens.domain.response;

import java.util.Map;
import lombok.Data;

@Data
public class ContentMetadata {

    private String name;
    private String contentType;
    private long length;
    private String tenantId;
    private String serviceId;
    private String userId;
    private Map<String,Object> metadata;
    private Long createdOn;

}
