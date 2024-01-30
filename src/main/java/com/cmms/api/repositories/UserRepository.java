package com.cmms.api.repositories;

import com.cmms.api.models.User;

import java.util.List;  // Asegúrate de importar la clase correcta List
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM public.users WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM public.technicians", nativeQuery = true)
    List<User> findAllTechnicians();

    @Query(value = "SELECT * FROM public.supervisors", nativeQuery = true)
    List<User> findAllSupervisors();

    @Query(value = "SELECT * FROM public.site_managers", nativeQuery = true)
    List<User> findAllSiteManagers();
}
