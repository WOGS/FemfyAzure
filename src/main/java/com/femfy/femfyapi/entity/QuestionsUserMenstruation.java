package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "questions_user_menstruation")
public class QuestionsUserMenstruation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "last_time")
    @Temporal(TemporalType.DATE)
    private Date lastTime;

    @Column(name = "last_cycle_duration")
    private Integer lastCycleDuration;

    @Column(name = "regular")
    private Boolean regular;

    @Column(name = "regular_cycle_duration")
    private Integer regularCycleDuration;

    @Column(name = "bleeding_duration")
    private Integer bleedingDuration;

    public QuestionsUserMenstruation() {
    }
}