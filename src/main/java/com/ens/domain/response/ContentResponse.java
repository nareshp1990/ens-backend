package com.ens.domain.response;

import java.util.UUID;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ContentResponse {

    private UUID id;
    private HttpStatus status;
    private String msg;
    private String url;
    private String downloadUrl;
    private ContentMetadata info;

    public ContentResponse(UUID id, HttpStatus status, String msg, String url,
            ContentMetadata info) {
        this.id = id;
        this.status = status;
        this.msg = msg;
        this.url = url;
        this.info = info;
    }

    public ContentResponse(UUID id, HttpStatus status, String msg, String url, String downloadUrl,
            ContentMetadata info) {
        this.id = id;
        this.status = status;
        this.msg = msg;
        this.url = url;
        this.downloadUrl = downloadUrl;
        this.info = info;
    }

    public ContentResponse() {
    }

}
