package com.cmms.api.repositories;

import com.cmms.api.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    
}
