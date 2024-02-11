package com.cmms.api.repositories;

import com.cmms.api.models.Workshop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

}
