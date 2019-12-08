package com.ens.service.impl;

import com.ens.domain.entity.NewsItemLocationInfo;
import com.ens.repo.NewsItemLocationInfoRepository;
import com.ens.service.NewsItemLocationInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsItemLocationInfoServiceImpl implements NewsItemLocationInfoService {

    @Autowired
    private NewsItemLocationInfoRepository repository;

    @Override
    public NewsItemLocationInfo save(NewsItemLocationInfo newsItemLocationInfo) {
        return repository.save(newsItemLocationInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NewsItemLocationInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<NewsItemLocationInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NewsItemLocationInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
