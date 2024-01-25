package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "remedial_actions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemedialAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "action", length = 255, nullable = false)
    private String action;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "register_date", length = 255, nullable = false)
    private String registerDate;

    @OneToMany(mappedBy = "remedialAction")
    @JsonIgnore
    private List<Check> checks;
}
