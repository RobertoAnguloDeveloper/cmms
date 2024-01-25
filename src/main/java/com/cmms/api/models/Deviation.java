package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "deviations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deviation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "deviation", length = 255, nullable = false)
    private String deviation;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "register_date", length = 255, nullable = false)
    private String registerDate;

    @OneToMany(mappedBy = "deviation")
    @JsonIgnore
    private List<Check> checks;
}
