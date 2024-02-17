package com.cmms.api.services;

import com.cmms.api.models.Deviation;
import com.cmms.api.repositories.DeviationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class DeviationService {

    @Autowired
    private DeviationRepository deviationRepository;

    @SuppressWarnings("null")
    public List<Deviation> getAllDeviations() {
        return deviationRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Deviation> getDeviationById(Integer id) {
        return deviationRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Deviation createDeviation(Deviation deviation) {
        return deviationRepository.save(deviation);
    }

    public Deviation updateDeviation(Integer id, Deviation deviation) {
        if (deviation != null && id != null) {
            Optional<Deviation> existingDeviation = deviationRepository.findById(id);

            if (existingDeviation.isPresent()) {
                Deviation updatedDeviation = existingDeviation.get();

                // Actualiza los campos no nulos
                if (deviation.getDeviation() != null) {
                    updatedDeviation.setDeviation(deviation.getDeviation());
                }
                if (deviation.getDescription() != null) {
                    updatedDeviation.setDescription(deviation.getDescription());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+3"));

                // Actualiza la fecha de modificación
                updatedDeviation.setModifyDate(now.toString());

                return deviationRepository.save(updatedDeviation);
            } else {
                System.out.println("Deviation not found");
                return null;
            }
        } else {
            System.out.println("Deviation NULL");
            return null;
        }
    }

    @SuppressWarnings("null")
    public void deleteDeviation(Integer id) {
        deviationRepository.deleteById(id);
    }
}
