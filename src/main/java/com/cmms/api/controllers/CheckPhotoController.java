package com.cmms.api.controllers;

import com.cmms.api.models.CheckPhoto;
import com.cmms.api.services.CheckPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkphoto")
@CrossOrigin(origins = "*")
public class CheckPhotoController {

    @Autowired
    private CheckPhotoService checkPhotoService;

    @PostMapping("/save")
    public ResponseEntity<CheckPhoto> createCheckPhoto(@RequestBody CheckPhoto checkPhoto) {
        CheckPhoto createdCheckPhoto = checkPhotoService.createCheckPhoto(checkPhoto);
        return ResponseEntity.ok(createdCheckPhoto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CheckPhoto>> getAllCheckPhotos() {
        List<CheckPhoto> checkPhotos = checkPhotoService.getAllCheckPhotos();
        return checkPhotos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(checkPhotos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckPhoto> getCheckPhotoById(@PathVariable("id") Integer id) {
        Optional<CheckPhoto> checkPhotoOptional = checkPhotoService.getCheckPhotoById(id);
        return checkPhotoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CheckPhoto> updateCheckPhoto(@PathVariable("id") Integer id, @RequestBody CheckPhoto checkPhoto) {
        CheckPhoto updatedCheckPhoto = checkPhotoService.updateCheckPhoto(id, checkPhoto);
        return updatedCheckPhoto != null ? ResponseEntity.ok(updatedCheckPhoto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckPhoto(@PathVariable("id") Integer id) {
        checkPhotoService.deleteCheckPhoto(id);
        return ResponseEntity.noContent().build();
    }
}
