package com.ens.service.news;

import com.ens.domain.entity.news.NewsItemSocialShare;
import com.ens.repo.news.NewsItemSocialShareRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsItemSocialShareServiceImpl implements NewsItemSocialShareService {

    @Autowired
    private NewsItemSocialShareRepository repository;

    @Override
    public NewsItemSocialShare save(NewsItemSocialShare newsItemSocialShare) {
        return repository.save(newsItemSocialShare);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NewsItemSocialShare> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<NewsItemSocialShare> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NewsItemSocialShare> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
