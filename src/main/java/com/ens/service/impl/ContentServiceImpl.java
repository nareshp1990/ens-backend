package com.ens.service.impl;

import com.ens.domain.entity.ContentInfo;
import com.ens.repo.ContentRepository;
import com.ens.service.ContentService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    /**
     *
     */
    @Override
    public ContentInfo save(ContentInfo contentInfo) {
        return contentRepository.save(contentInfo);
    }

    /**
     *
     */
    @Override
    public void delete(UUID id) {
        contentRepository.deleteById(id);
    }

    /**
     *
     */
    @Override
    public Optional<ContentInfo> findOne(UUID id) {
        return contentRepository.findById(id);
    }

    /**
     *
     */
    @Override
    public Iterable<ContentInfo> findAll() {
        return contentRepository.findAll();
    }

    /**
     *
     */
    @Override
    public Page<ContentInfo> findAll(Pageable pgbl) {
        return contentRepository.findAll(pgbl);
    }

    @Override
    public void deleteContent(String userId, String filePath, String fileName) {
        contentRepository.deleteContent(userId, filePath, fileName);
    }

    @Override
    public boolean isAllowedToDelete(String userId, String filePath, String fileName) {
        return contentRepository.isAllowedToDelete(userId, filePath, fileName);
    }

    @Override
    public List<String> getFileNames(String userId, String filePath) {
        return contentRepository.getFileNames(userId, filePath);
    }

}
