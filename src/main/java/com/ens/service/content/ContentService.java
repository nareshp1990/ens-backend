package com.ens.service.content;

import com.ens.domain.entity.content.ContentInfo;
import com.ens.service.IService;
import java.util.List;

public interface ContentService extends IService<ContentInfo> {

    void deleteContent(String userId,String filePath,String fileName);

    boolean isAllowedToDelete(String userId,String filePath,String fileName);

    List<String> getFileNames(String userId,String filePath);

}