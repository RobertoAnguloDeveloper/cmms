package com.cmms.api.services;

import com.cmms.api.models.Role;
import com.cmms.api.repositories.RoleRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        // Verifica si los roles ya existen en la base de datos
        if (roleRepository.findAll().isEmpty()) {
            // Si no existen, crea los roles iniciales
            createRole("Supervisor",
                    "Can create users, questions, answers, deviations, remedial actions, workshops, checks and photos.");
            createRole("Technician", "Can create checks and photos");
            createRole("Site manager",
                    "Can create users, questions, answers, deviations, remedial actions, workshops, checks and photos.");
            createRole("Superuser", "Domain of the whole database");
        }
    }

    private void createRole(String roleName, String description) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        // Puedes establecer la fecha de registro y otros campos aquí si es necesario
        roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @SuppressWarnings("null")
    public Role createRole(Role role) {
        // Puedes realizar validaciones u operaciones adicionales antes de guardar el
        // rol
        return roleRepository.save(role);
    }

    @SuppressWarnings("null")
    public Role updateRole(Role role) {
        if (role != null && role.getId() != null) {
            Optional<Role> existingRole = roleRepository.findById(role.getId());

            if (existingRole.isPresent()) {
                Role updatedRole = existingRole.get();

                // Actualiza los campos no nulos
                if (role.getRoleName() != null) {
                    updatedRole.setRoleName(role.getRoleName());
                }
                if (role.getDescription() != null) {
                    updatedRole.setDescription(role.getDescription());
                }

                // Obtiene la fecha y hora actual en UTC+3
                LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-6"));

                // Actualiza la fecha de modificación
                updatedRole.setModifyDate(now.toString());

                return roleRepository.save(updatedRole);
            } else {
                return null; // Manejar el caso de rol no encontrado
            }
        } else {
            return null; // Manejar el caso de rol nulo o sin ID
        }
    }

    @SuppressWarnings("null")
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
