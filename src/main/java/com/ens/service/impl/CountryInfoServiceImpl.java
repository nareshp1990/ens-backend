package com.ens.service.impl;

import com.ens.domain.entity.CountryInfo;
import com.ens.repo.CountryInfoRepository;
import com.ens.service.CountryInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryInfoServiceImpl implements CountryInfoService {

    @Autowired
    private CountryInfoRepository repository;

    @Override
    public CountryInfo save(CountryInfo countryInfo) {
        return repository.save(countryInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<CountryInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<CountryInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<CountryInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
