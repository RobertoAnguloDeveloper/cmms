package com.cmms.api.services;

import com.cmms.api.models.Check;
import com.cmms.api.repositories.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CheckService {

    @Autowired
    private CheckRepository checkRepository;

    public List<Check> getAllChecks() {
        return checkRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Check> getCheckById(Integer id) {
        return checkRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Check createCheck(Check check) {
        return checkRepository.save(check);
    }

    public Check updateCheck(Integer id, Check check) {
        if (check != null && id != null) {
            Optional<Check> existingCheck = checkRepository.findById(id);
    
            if (existingCheck.isPresent()) {
                Check updatedCheck = existingCheck.get();
    
                // Actualiza los campos no nulos
                if (check.getQuestionId() != null) {
                    updatedCheck.setQuestionId(check.getQuestionId());
                }
                if (check.getWorkshopId() != null) {
                    updatedCheck.setWorkshopId(check.getWorkshopId());
                }
                if (check.getAnswerId() != null) {
                    updatedCheck.setAnswerId(check.getAnswerId());
                }
                if (check.getRemedialId() != null) {
                    updatedCheck.setRemedialId(check.getRemedialId());
                }
                if (check.getDeviationId() != null) {
                    updatedCheck.setDeviationId(check.getDeviationId());
                }
                if (check.getUserId() != null) {
                    updatedCheck.setUserId(check.getUserId());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-6"));
    
                // Actualiza la fecha de modificación
                updatedCheck.setModifyDate(now.toString());
    
                return checkRepository.save(updatedCheck);
            } else {
                System.out.println("Check not found");
                return null;
            }
        } else {
            System.out.println("Check or ID is NULL");
            return null;
        }
    }
    

    @SuppressWarnings("null")
    public void deleteCheck(Integer id) {
        checkRepository.deleteById(id);
    }
}
