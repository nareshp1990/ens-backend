package com.ens.service.location;

import com.ens.domain.entity.location.State;
import com.ens.repo.location.StateRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository repository;

    @Override
    public State save(State state) {
        return repository.save(state);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<State> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<State> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<State> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
