package com.ens.service.impl;

import com.ens.domain.entity.NewsItemInfo;
import com.ens.repo.NewsItemInfoRepository;
import com.ens.service.NewsItemInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsItemInfoServiceImpl implements NewsItemInfoService {

    @Autowired
    private NewsItemInfoRepository repository;

    @Override
    public NewsItemInfo save(NewsItemInfo newsItemInfo) {
        return repository.save(newsItemInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NewsItemInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<NewsItemInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NewsItemInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
