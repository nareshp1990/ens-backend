package com.ens.service;

import com.ens.domain.entity.ContentInfo;
import com.ens.exception.ContentNotFoundException;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContentValidationService {

    @Autowired
    private ContentService contentService;

    /**
     * @param id content id
     * @return
     */
    public ContentInfo validateAndGet(UUID id){

        Optional<ContentInfo> contentInfo = contentService.findOne(id);

        contentInfo.orElseThrow(() -> new ContentNotFoundException(" Content not found fo this id " + id));

        return contentInfo.get();
    }

}
