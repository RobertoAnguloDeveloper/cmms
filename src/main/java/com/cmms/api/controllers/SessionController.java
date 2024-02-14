package com.cmms.api.controllers;

import com.cmms.api.models.Session;
import com.cmms.api.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "*")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/save")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        Session createdSession = sessionService.saveSession(session);
        return ResponseEntity.ok(createdSession);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.getAllSessions();
        if (sessions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(sessions);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable("id") Integer id) {
        Optional<Session> sessionOptional = sessionService.getSessionById(id);

        return sessionOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable("id") Integer id, @RequestBody Session session) {
        Session updatedSession = sessionService.updateSession(id, session);
        if (updatedSession == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedSession);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable("id") Integer id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
