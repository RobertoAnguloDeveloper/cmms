package com.cmms.api.services;

import com.cmms.api.models.Photo;
import com.cmms.api.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Optional<Photo> getPhotoById(Integer id) {
        return photoRepository.findById(id);
    }

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo updatePhoto(Integer id, Photo photo) {
        if (photo != null && photo.getId() != null) {
            Optional<Photo> existingPhoto = photoRepository.findById(id);

            if (existingPhoto.isPresent()) {
                Photo updatedPhoto = existingPhoto.get();
                updatedPhoto.setFileName(photo.getFileName());
                updatedPhoto.setContent(photo.getContent());
                updatedPhoto.setRegisterDate(photo.getRegisterDate());
                // Puedes actualizar otros campos aquí

                return photoRepository.save(updatedPhoto);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }

    public Photo uploadPhoto(MultipartFile file, String fileName, String registerDate) throws IOException {
        Photo photo = new Photo();
        photo.setFileName(fileName);
        photo.setContent(file.getBytes());
        photo.setRegisterDate(registerDate);

        return photoRepository.save(photo);
    }

    public byte[] downloadPhoto(Integer id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        return photoOptional.map(Photo::getContent).orElse(null);
    }
}
