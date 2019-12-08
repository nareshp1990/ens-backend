package com.ens.service.impl;

import com.ens.domain.entity.UserLike;
import com.ens.repo.UserLikeRepository;
import com.ens.service.UserLikeService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private UserLikeRepository repository;

    @Override
    public UserLike save(UserLike userLike) {
        return repository.save(userLike);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserLike> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserLike> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserLike> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
