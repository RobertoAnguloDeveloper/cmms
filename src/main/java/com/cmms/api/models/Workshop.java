package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "workshops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "workshop", length = 255, nullable = false)
    private String workshop;

    @Column(name = "modify_date", length = 255)
    private String modifyDate;

    @Column(name = "register_date", length = 255)
    private String registerDate;

    @OneToMany(mappedBy = "workshop")
    @JsonIgnore
    private List<Check> checks;
}
