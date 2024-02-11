package com.cmms.api.controllers;

import com.cmms.api.models.Deviation;
import com.cmms.api.services.DeviationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deviation")
@CrossOrigin(origins = "*")
public class DeviationController {

    @Autowired
    private DeviationService deviationService;

    @PostMapping("/save")
    public ResponseEntity<Deviation> createDeviation(@RequestBody Deviation deviation) {
        Deviation createdDeviation = deviationService.createDeviation(deviation);
        return ResponseEntity.ok(createdDeviation);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Deviation>> getAllDeviations() {
        List<Deviation> deviations = deviationService.getAllDeviations();
        if (deviations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(deviations);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deviation> getDeviationById(@PathVariable("id") Integer id) {
        Optional<Deviation> deviationOptional = deviationService.getDeviationById(id);

        return deviationOptional.map(deviation -> ResponseEntity.ok(deviation))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Deviation> updateDeviation(@PathVariable("id") Integer id, @RequestBody Deviation deviation) {
        Deviation updatedDeviation = deviationService.updateDeviation(id, deviation);
        if (updatedDeviation == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedDeviation);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviation(@PathVariable("id") Integer id) {
        deviationService.deleteDeviation(id);
        return ResponseEntity.noContent().build();
    }
}
