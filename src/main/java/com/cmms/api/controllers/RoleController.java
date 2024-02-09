package com.cmms.api.controllers;

import com.cmms.api.models.Role;
import com.cmms.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(roles);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Integer id) {
        Optional<Role> roleOptional = roleService.getRoleById(id);

        return roleOptional.map(role -> ResponseEntity.ok(role))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        Role updatedRole = roleService.updateRole(role);
        if (updatedRole == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedRole);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
