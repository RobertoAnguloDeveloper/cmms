package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question", length = 255, nullable = false)
    private String question;

    @Column(name = "register_date", length = 255, nullable = false)
    private String registerDate;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private List<Check> checks;
}
