package com.ens.service.impl;

import com.ens.domain.entity.DistrictInfo;
import com.ens.repo.DistrictInfoRepository;
import com.ens.service.DistrictInfoService;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DistrictInfoServiceImpl implements DistrictInfoService {

    @Autowired
    private DistrictInfoRepository repository;

    @Override
    public DistrictInfo save(DistrictInfo districtInfo) {
        return repository.save(districtInfo);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<DistrictInfo> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<DistrictInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<DistrictInfo> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
