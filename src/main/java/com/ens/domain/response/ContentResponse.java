package com.ens.domain.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ContentResponse {

    private Long id;
    private HttpStatus status;
    private String msg;
    private String url;
    private String downloadUrl;
    private ContentMetadata info;

    public ContentResponse(Long id, HttpStatus status, String msg, String url,
            ContentMetadata info) {
        this.id = id;
        this.status = status;
        this.msg = msg;
        this.url = url;
        this.info = info;
    }

    public ContentResponse(Long id, HttpStatus status, String msg, String url, String downloadUrl,
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
