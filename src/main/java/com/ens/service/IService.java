package com.ens.service;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

    T save(T t);

    void delete(UUID id);

    Optional<T> findOne(UUID id);

    Iterable<T> findAll();

    Page<T> findAll(Pageable pgbl);

}
