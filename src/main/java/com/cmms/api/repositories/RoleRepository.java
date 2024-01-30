package com.cmms.api.repositories;

import com.cmms.api.models.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "SELECT * FROM public.roles WHERE role_name = :role_name", nativeQuery = true)
    Optional<Role> findByRoleName(@Param("role_name") String role_name);
}
