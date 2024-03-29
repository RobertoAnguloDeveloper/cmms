package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "answer", length = 255, nullable = false)
    private String answer;

    @Column(name = "register_date", length = 255)
    private String registerDate;

    @Column(name = "modify_date", length = 255)
    private String modifyDate;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "answer")
    @JsonIgnore
    private List<Check> checks;
}
