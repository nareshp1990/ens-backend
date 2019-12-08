package com.ens.service.impl;

import com.ens.domain.entity.PollTransaction;
import com.ens.repo.PollTransactionRepository;
import com.ens.service.PollTransactionService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PollTransactionServiceImpl implements PollTransactionService {

    @Autowired
    private PollTransactionRepository repository;

    @Override
    public PollTransaction save(PollTransaction pollTransaction) {
        return repository.save(pollTransaction);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PollTransaction> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<PollTransaction> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<PollTransaction> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
