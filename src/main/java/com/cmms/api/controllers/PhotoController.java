package com.cmms.api.controllers;

import com.cmms.api.models.Photo;
import com.cmms.api.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/all")
    public ResponseEntity<List<Photo>> getAllPhotos() {
        List<Photo> photos = photoService.getAllPhotos();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Integer id) {
        Optional<Photo> optionalPhoto = photoService.getPhotoById(id);

        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            return new ResponseEntity<>(photo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("file") MultipartFile file) throws Exception {
        try {
            Photo createdPhoto = photoService.uploadPhoto(file);
            return new ResponseEntity<>(createdPhoto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings("null")
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable Integer id) {
        Optional<Photo> optionalPhoto = photoService.getPhotoById(id);

        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto.get();
            byte[] content = photoService.downloadPhoto(id);

            if (content != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(photo.getFileType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + photo.getFileName()+ "\"")
                        .body(new ByteArrayResource(photo.getContent()));
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable Integer id, @RequestBody Photo photo) {
        Photo updatedPhoto = photoService.updatePhoto(id, photo.getFileName());
        return (updatedPhoto != null) ? new ResponseEntity<>(updatedPhoto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Integer id) {
        photoService.deletePhoto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
