package com.ens.service.location;

import com.ens.domain.entity.location.Country;
import com.ens.repo.location.CountryRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public Country save(Country country) {
        return repository.save(country);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Country> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Country> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Country> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
