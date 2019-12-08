package com.ens.service.impl;

import com.ens.domain.entity.StateInfo;
import com.ens.repo.StateInfoRepository;
import com.ens.service.StateInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StateInfoServiceImpl implements StateInfoService {

    @Autowired
    private StateInfoRepository repository;

    @Override
    public StateInfo save(StateInfo stateInfo) {
        return repository.save(stateInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<StateInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<StateInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<StateInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
