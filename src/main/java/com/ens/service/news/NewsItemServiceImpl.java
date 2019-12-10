package com.ens.service.news;

import com.ens.domain.entity.news.NewsItem;
import com.ens.repo.news.NewsItemRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsItemServiceImpl implements NewsItemService {

    @Autowired
    private NewsItemRepository repository;

    @Override
    public NewsItem save(NewsItem newsItem) {
        return repository.save(newsItem);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NewsItem> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<NewsItem> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NewsItem> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
