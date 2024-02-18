package com.cmms.api.services;

import com.cmms.api.models.RemedialAction;
import com.cmms.api.repositories.RemedialActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RemedialActionService {

    @Autowired
    private RemedialActionRepository remedialActionRepository;

    public List<RemedialAction> getAllRemedialActions() {
        return remedialActionRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<RemedialAction> getRemedialActionById(Integer id) {
        return remedialActionRepository.findById(id);
    }

    @SuppressWarnings("null")
    public RemedialAction createRemedialAction(RemedialAction remedialAction) {
        return remedialActionRepository.save(remedialAction);
    }

    public RemedialAction updateRemedialAction(Integer id, RemedialAction remedialAction) {
        if (remedialAction != null && id != null) {
            Optional<RemedialAction> existingRemedialAction = remedialActionRepository.findById(id);

            if (existingRemedialAction.isPresent()) {
                RemedialAction updatedRemedialAction = existingRemedialAction.get();

                // Actualiza los campos no nulos
                if (remedialAction.getAction() != null) {
                    updatedRemedialAction.setAction(remedialAction.getAction());
                }
                if (remedialAction.getDescription() != null) {
                    updatedRemedialAction.setDescription(remedialAction.getDescription());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-3"));

                // Actualiza la fecha de modificación
                updatedRemedialAction.setModifyDate(now.toString());

                return remedialActionRepository.save(updatedRemedialAction);
            } else {
                System.out.println("Remedial Action not found");
                return null;
            }
        } else {
            System.out.println("Remedial Action or ID is NULL");
            return null;
        }
    }

    @SuppressWarnings("null")
    public void deleteRemedialAction(Integer id) {
        remedialActionRepository.deleteById(id);
    }
}
