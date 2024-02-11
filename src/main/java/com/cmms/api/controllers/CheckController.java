package com.cmms.api.controllers;

import com.cmms.api.models.Check;
import com.cmms.api.services.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/check")
@CrossOrigin(origins = "*")
public class CheckController {

    @Autowired
    private CheckService checkService;

    @PostMapping("/save")
    public ResponseEntity<Check> createCheck(@RequestBody Check check) {
        Check createdCheck = checkService.createCheck(check);
        return ResponseEntity.ok(createdCheck);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Check>> getAllChecks() {
        List<Check> checks = checkService.getAllChecks();
        return checks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(checks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Check> getCheckById(@PathVariable("id") Integer id) {
        Optional<Check> checkOptional = checkService.getCheckById(id);
        return checkOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Check> updateCheck(@PathVariable("id") Integer id, @RequestBody Check check) {
        Check updatedCheck = checkService.updateCheck(id, check);
        return updatedCheck != null ? ResponseEntity.ok(updatedCheck) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheck(@PathVariable("id") Integer id) {
        checkService.deleteCheck(id);
        return ResponseEntity.noContent().build();
    }
}
