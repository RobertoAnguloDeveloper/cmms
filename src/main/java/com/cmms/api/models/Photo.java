package com.cmms.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "photos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    
    @Column(name = "content", nullable = false)
    private byte[] content;

    @Column(name = "file_type", length = 255, nullable = false)
    private String fileType;

    @Column(name = "register_date", length = 255)
    private String registerDate;

    @OneToMany(mappedBy = "photo")
    @JsonIgnore
    private List<Check> checks;
}
