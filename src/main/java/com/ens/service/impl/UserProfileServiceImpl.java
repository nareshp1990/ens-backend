package com.ens.service.impl;

import com.ens.domain.entity.UserProfile;
import com.ens.repo.UserProfileRepository;
import com.ens.service.UserProfileService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    @Override
    public UserProfile save(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<UserProfile> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<UserProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<UserProfile> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
