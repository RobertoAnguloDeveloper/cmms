package com.cmms.api.repositories;

import com.cmms.api.models.Session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {

}
