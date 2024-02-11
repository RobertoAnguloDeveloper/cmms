package com.cmms.api.services;

import com.cmms.api.models.Photo;
import com.cmms.api.repositories.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Photo updatePhoto(Integer id, Photo photo) {
        if (photo != null && photo.getId() != null) {
            @SuppressWarnings("null")
            Optional<Photo> existingPhoto = photoRepository.findById(id);

            if (existingPhoto.isPresent()) {
                Photo updatedPhoto = existingPhoto.get();
                updatedPhoto.setFileName(photo.getFileName());
                updatedPhoto.setContent(photo.getContent());
                // Puedes actualizar otros campos aquí

                return photoRepository.save(updatedPhoto);
            } else {
                return null;
            }
        } else {
            return null;
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
