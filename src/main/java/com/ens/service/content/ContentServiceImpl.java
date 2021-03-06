package com.ens.service.content;

import com.ens.domain.entity.content.ContentInfo;
import com.ens.repo.content.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    /**
     *
     */
     @Transactional
    @Override
    public ContentInfo save(ContentInfo contentInfo) {
        return contentRepository.save(contentInfo);
    }

    /**
     *
     */
    @Override
    public void delete(Long id) {
        contentRepository.deleteById(id);
    }

    /**
     *
     */
    @Override
    public Optional<ContentInfo> findOne(Long id) {
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

    @Transactional
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
