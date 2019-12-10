package com.ens.service.location;

import com.ens.domain.entity.location.Area;
import com.ens.repo.location.AreaRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Override
    public Area save(Area area) {
        return repository.save(area);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Area> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Area> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Area> findAll(Pageable pgbl) {
        return repository.findAll(pgbl);
    }
}
