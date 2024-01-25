package com.cmms.api.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "checks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_id", nullable = false)
    private Integer questionId;

    @Column(name = "worshop_id", nullable = false)
    private Integer workshopId;

    @Column(name = "answer_id", nullable = false)
    private Integer answerId;

    @Column(name = "remedial_id", nullable = false)
    private Integer remedialId;

    @Column(name = "deviation_id", nullable = false)
    private Integer deviationId;

    @Column(name = "photos_id", nullable = false)
    private Integer photosId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "register_date", length = 255)
    private String registerDate;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Check question;

    @ManyToOne
    @JoinColumn(name = "worshop_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Workshop workshop;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "remedial_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private RemedialAction remedialAction;

    @ManyToOne
    @JoinColumn(name = "deviation_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Deviation deviation;

    @ManyToOne
    @JoinColumn(name = "photos_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

}
