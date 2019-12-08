package com.ens.service.impl;

import com.ens.domain.entity.PollInfo;
import com.ens.repo.PollInfoRepository;
import com.ens.service.PollInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollInfoServiceImpl implements PollInfoService {

    @Autowired
    private PollInfoRepository repository;

    @Override
    public PollInfo save(PollInfo pollInfo) {
        return repository.save(pollInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PollInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<PollInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<PollInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
