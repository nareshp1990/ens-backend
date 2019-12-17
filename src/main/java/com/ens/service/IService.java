package com.ens.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IService<T> {

    T save(T t);

    void delete(Long id);

    Optional<T> findOne(Long id);

    Iterable<T> findAll();

    Page<T> findAll(Pageable pgbl);

}
