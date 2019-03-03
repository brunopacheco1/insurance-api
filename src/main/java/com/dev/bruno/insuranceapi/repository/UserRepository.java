package com.dev.bruno.insuranceapi.repository;

import com.dev.bruno.insuranceapi.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
