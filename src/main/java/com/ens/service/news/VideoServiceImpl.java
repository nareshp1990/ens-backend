package com.ens.service.news;

import com.ens.domain.entity.news.Video;
import com.ens.repo.news.VideoRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    private VideoRepository repository;

    @Override
    public Video save(Video video) {
        return repository.save(video);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Video> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Video> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Video> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
