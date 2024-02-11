package com.cmms.api.controllers;

import com.cmms.api.models.RemedialAction;
import com.cmms.api.services.RemedialActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/remedialaction")
@CrossOrigin(origins = "*")
public class RemedialActionController {

    @Autowired
    private RemedialActionService remedialActionService;

    @PostMapping("/save")
    public ResponseEntity<RemedialAction> createRemedialAction(@RequestBody RemedialAction remedialAction) {
        RemedialAction createdRemedialAction = remedialActionService.createRemedialAction(remedialAction);
        return ResponseEntity.ok(createdRemedialAction);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RemedialAction>> getAllRemedialActions() {
        List<RemedialAction> remedialActions = remedialActionService.getAllRemedialActions();
        return remedialActions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(remedialActions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemedialAction> getRemedialActionById(@PathVariable("id") Integer id) {
        Optional<RemedialAction> remedialActionOptional = remedialActionService.getRemedialActionById(id);
        return remedialActionOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RemedialAction> updateRemedialAction(@PathVariable("id") Integer id, @RequestBody RemedialAction remedialAction) {
        RemedialAction updatedRemedialAction = remedialActionService.updateRemedialAction(id, remedialAction);
        return updatedRemedialAction != null ? ResponseEntity.ok(updatedRemedialAction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemedialAction(@PathVariable("id") Integer id) {
        remedialActionService.deleteRemedialAction(id);
        return ResponseEntity.noContent().build();
    }
}
