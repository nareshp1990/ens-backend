package com.ens.service.news;

import com.ens.domain.entity.news.NewsItemLocation;
import com.ens.repo.news.NewsItemLocationInfoRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NewsItemLocationServiceImpl implements NewsItemLocationService {

    @Autowired
    private NewsItemLocationInfoRepository repository;

    @Override
    public NewsItemLocation save(NewsItemLocation newsItemLocation) {
        return repository.save(newsItemLocation);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<NewsItemLocation> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<NewsItemLocation> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<NewsItemLocation> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
