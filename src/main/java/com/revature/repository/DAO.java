package com.revature.repository;

import java.util.List;
import java.util.Optional;

public interface DAO<T, I> {

    // create
    Optional<T> create(T t);
    // read
    Optional<T> getById(I id);
    List<T> getAll();
    // update
    Optional<T> updateById(T t, I id);
    // delete
    Optional<T> deleteById(I id);
}
