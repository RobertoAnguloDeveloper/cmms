package com.cmms.api.services;

import com.cmms.api.models.Session;
import com.cmms.api.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Optional<Session> getSessionById(Integer id) {
        return sessionRepository.findById(id);
    }

    public Optional<Session> getSessionByToken(String token) {
        return sessionRepository.findByToken(token);
    }

    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session updateSession(Integer id, Session session) {
        if (session != null && id != null) {
            Optional<Session> existingSession = sessionRepository.findById(id);

            if (existingSession.isPresent()) {
                Session updatedSession = existingSession.get();

                // Actualiza los campos no nulos
                if (session.getToken() != null) {
                    updatedSession.setToken(session.getToken());
                }
                if (session.getUsername() != null) {
                    updatedSession.setUsername(session.getUsername());
                }
                if (session.getModifyDate() != null) {
                    updatedSession.setModifyDate(session.getModifyDate());
                }
                if (session.getRegisterDate() != null) {
                    updatedSession.setRegisterDate(session.getRegisterDate());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-3"));

                // Actualiza la fecha de modificación
                updatedSession.setModifyDate(now.toString());

                return sessionRepository.save(updatedSession);
            } else {
                System.out.println("Session not found");
                return null;
            }
        } else {
            System.out.println("Session or ID is NULL");
            return null;
        }
    }

    public void deleteSession(Integer id) {
        sessionRepository.deleteById(id);
    }
}
