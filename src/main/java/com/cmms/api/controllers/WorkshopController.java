package com.cmms.api.controllers;

import com.cmms.api.models.Workshop;
import com.cmms.api.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workshop")
@CrossOrigin(origins = "*")
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping("/save")
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop) {
        Workshop createdWorkshop = workshopService.createWorkshop(workshop);
        return ResponseEntity.ok(createdWorkshop);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Workshop>> getAllWorkshops() {
        List<Workshop> workshops = workshopService.getAllWorkshops();
        return workshops.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(workshops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable("id") Integer id) {
        Optional<Workshop> workshopOptional = workshopService.getWorkshopById(id);
        return workshopOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable("id") Integer id, @RequestBody Workshop workshop) {
        Workshop updatedWorkshop = workshopService.updateWorkshop(id, workshop);
        return updatedWorkshop != null ? ResponseEntity.ok(updatedWorkshop) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable("id") Integer id) {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }
}