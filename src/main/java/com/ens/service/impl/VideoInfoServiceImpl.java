package com.ens.service.impl;

import com.ens.domain.entity.VideoInfo;
import com.ens.repo.VideoInfoRepository;
import com.ens.service.VideoInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VideoInfoServiceImpl implements VideoInfoService {

    private VideoInfoRepository repository;

    @Override
    public VideoInfo save(VideoInfo videoInfo) {
        return repository.save(videoInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<VideoInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<VideoInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<VideoInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
