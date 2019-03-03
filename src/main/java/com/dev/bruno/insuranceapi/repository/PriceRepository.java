package com.dev.bruno.insuranceapi.repository;

import com.dev.bruno.insuranceapi.domain.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PriceRepository extends CrudRepository<Price, Long> {

    List<Price> findAllByUserId(Long userId);
}
