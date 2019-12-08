package com.ens.service.impl;

import com.ens.domain.entity.UserUnLike;
import com.ens.repo.UserUnLikeRepository;
import com.ens.service.UserUnLikeService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserUnLikeServiceImpl implements UserUnLikeService {

    @Autowired
    private UserUnLikeRepository repository;

    @Override
    public UserUnLike save(UserUnLike userUnLike) {
        return repository.save(userUnLike);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserUnLike> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserUnLike> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserUnLike> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
