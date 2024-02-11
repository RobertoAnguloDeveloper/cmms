package com.cmms.api.repositories;

import com.cmms.api.models.Check;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Check, Integer> {

}
