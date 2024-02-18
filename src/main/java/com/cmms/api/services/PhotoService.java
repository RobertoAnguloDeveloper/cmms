package com.cmms.api.services;

import com.cmms.api.models.Photo;
import com.cmms.api.repositories.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Photo> getPhotoById(Integer id) {
        return photoRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @SuppressWarnings("null")
    public Photo updatePhoto(Integer id, String fileName) {
        Optional<Photo> existingPhoto = photoRepository.findById(id);

        if (existingPhoto.isPresent()) {
            Photo photo = existingPhoto.get();
            photo.setFileName(fileName);

            // Obtiene la fecha y hora actual en UTC+3
            LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-3"));

            // Actualiza la fecha de modificación
            photo.setModifyDate(now.toString());
            return photoRepository.save(photo);
        } else {
            return null; // Manejar el caso de foto no encontrada
        }
    }

    @SuppressWarnings("null")
    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }

    public Photo uploadPhoto(MultipartFile file) throws Exception {
        Photo photo = new Photo();
        photo.setFileName(file.getOriginalFilename()); // No necesitas limpiar el nombre del archivo
        try {
            photo.setFileType(file.getContentType());
            photo.setContent(file.getBytes());
            return photoRepository.save(photo);
        } catch (Exception e) {
            throw new Exception("Could not save the file " + file.getOriginalFilename(), e);
        }
    }

    public byte[] downloadPhoto(Integer id) {
        @SuppressWarnings("null")
        Optional<Photo> photoOptional = photoRepository.findById(id);
        return photoOptional.map(Photo::getContent).orElse(null);
    }
}
