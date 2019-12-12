package com.ens.domain.payload.content;

import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentRequest {

    private String filePath;
    private String fileName;
    private String tenantId;
    private String userId;
    private String serviceId;
    private MultipartFile file;
    private HttpServletRequest httpServletRequest;


}
