package com.cmms.api.services;

import com.cmms.api.models.Workshop;
import com.cmms.api.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Workshop> getWorkshopById(Integer id) {
        return workshopRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Workshop createWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshop(Integer id, Workshop workshop) {
        if (workshop != null && id != null) {
            Optional<Workshop> existingWorkshop = workshopRepository.findById(id);

            if (existingWorkshop.isPresent()) {
                Workshop updatedWorkshop = existingWorkshop.get();

                // Actualiza los campos no nulos
                if (workshop.getWorkshop() != null) {
                    updatedWorkshop.setWorkshop(workshop.getWorkshop());
                }

                // Actualiza la fecha de modificación
                updatedWorkshop.setModifyDate(LocalDateTime.now().toString());

                return workshopRepository.save(updatedWorkshop);
            } else {
                System.out.println("Workshop not found");
                return null;
            }
        } else {
            System.out.println("Workshop or ID is NULL");
            return null;
        }
    }

    @SuppressWarnings("null")
    public void deleteWorkshop(Integer id) {
        workshopRepository.deleteById(id);
    }
}
