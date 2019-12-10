package com.ens.service.location;

import com.ens.domain.entity.location.District;
import com.ens.repo.location.DistrictRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository repository;

    @Override
    public District save(District district) {
        return repository.save(district);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<District> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<District> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<District> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
