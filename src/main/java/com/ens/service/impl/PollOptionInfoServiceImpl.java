package com.ens.service.impl;

import com.ens.domain.entity.PollOptionInfo;
import com.ens.repo.PollOptionInfoRepository;
import com.ens.service.PollOptionInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollOptionInfoServiceImpl implements PollOptionInfoService {

    @Autowired
    private PollOptionInfoRepository repository;

    @Override
    public PollOptionInfo save(PollOptionInfo pollOptionInfo) {
        return repository.save(pollOptionInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PollOptionInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<PollOptionInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<PollOptionInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
