package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", nullable = false, length = 255)
    private String roleName;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "register_date", length = 255)
    private String registerDate;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> users;
}
