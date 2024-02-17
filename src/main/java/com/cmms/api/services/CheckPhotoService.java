package com.cmms.api.services;

import com.cmms.api.models.CheckPhoto;
import com.cmms.api.repositories.CheckPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CheckPhotoService {

    @Autowired
    private CheckPhotoRepository checkPhotoRepository;

    public List<CheckPhoto> getAllCheckPhotos() {
        return checkPhotoRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<CheckPhoto> getCheckPhotoById(Integer id) {
        return checkPhotoRepository.findById(id);
    }

    @SuppressWarnings("null")
    public CheckPhoto createCheckPhoto(CheckPhoto checkPhoto) {
        return checkPhotoRepository.save(checkPhoto);
    }

    public CheckPhoto updateCheckPhoto(Integer id, CheckPhoto checkPhoto) {
        if (checkPhoto != null && id != null) {
            Optional<CheckPhoto> existingCheckPhoto = checkPhotoRepository.findById(id);

            if (existingCheckPhoto.isPresent()) {
                CheckPhoto updatedCheckPhoto = existingCheckPhoto.get();

                // Actualiza los campos no nulos
                if (checkPhoto.getCheck() != null) {
                    updatedCheckPhoto.setCheck(checkPhoto.getCheck());
                }
                if (checkPhoto.getPhoto() != null) {
                    updatedCheckPhoto.setPhoto(checkPhoto.getPhoto());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+3"));

                // Actualiza la fecha de modificación
                updatedCheckPhoto.setModifyDate(now.toString());

                return checkPhotoRepository.save(updatedCheckPhoto);
            } else {
                System.out.println("CheckPhoto not found");
                return null;
            }
        } else {
            System.out.println("CheckPhoto or ID is NULL");
            return null;
        }
    }

    @SuppressWarnings("null")
    public void deleteCheckPhoto(Integer id) {
        checkPhotoRepository.deleteById(id);
    }
}
