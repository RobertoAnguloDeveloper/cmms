package com.cmms.api.repositories;

import com.cmms.api.models.Deviation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviationRepository extends JpaRepository<Deviation, Integer> {

}
