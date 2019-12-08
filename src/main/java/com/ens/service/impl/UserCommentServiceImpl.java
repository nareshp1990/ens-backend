package com.ens.service.impl;

import com.ens.domain.entity.UserComment;
import com.ens.repo.UserCommentRepository;
import com.ens.service.UserCommentService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserCommentServiceImpl implements UserCommentService {

    @Autowired
    private UserCommentRepository repository;

    @Override
    public UserComment save(UserComment userComment) {
        return repository.save(userComment);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserComment> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserComment> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserComment> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
