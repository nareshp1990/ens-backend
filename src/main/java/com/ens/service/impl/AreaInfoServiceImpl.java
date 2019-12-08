package com.ens.service.impl;

import com.ens.domain.entity.AreaInfo;
import com.ens.repo.AreaInfoRepository;
import com.ens.service.AreaInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AreaInfoServiceImpl implements AreaInfoService {

    @Autowired
    private AreaInfoRepository repository;

    @Override
    public AreaInfo save(AreaInfo areaInfo) {
        return repository.save(areaInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<AreaInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<AreaInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<AreaInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
