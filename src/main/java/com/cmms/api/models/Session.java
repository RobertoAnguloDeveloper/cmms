package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "modify_date", length = 255)
    private String modifyDate;

    @Column(name = "register_date", length = 255)
    private String registerDate;
}
