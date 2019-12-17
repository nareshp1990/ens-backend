package com.ens.service.content;

import com.ens.domain.entity.content.ContentInfo;
import com.ens.exception.ContentNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ContentValidationService {

    @Autowired
    private ContentService contentService;

    /**
     * @param id content id
     * @return
     */
    public ContentInfo validateAndGet(Long id){

        Optional<ContentInfo> contentInfo = contentService.findOne(id);

        contentInfo.orElseThrow(() -> new ContentNotFoundException(" Content not found fo this id " + id));

        return contentInfo.get();
    }

}
