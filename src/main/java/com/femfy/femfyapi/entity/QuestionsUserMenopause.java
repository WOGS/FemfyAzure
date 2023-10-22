package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions_user_menopause")
public class QuestionsUserMenopause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "suffocation")
    private Boolean suffocation;

    @Column(name = "changes_in_menstrual_cycle")
    private Boolean changesInMenstrualCycle;

    @Column(name = "vaginal_dryness")
    private Boolean vaginalDryness;

    @Column(name = "changes_in_skin_and_hair")
    private Boolean changesInSkinAndHair;

    @Column(name = "mood_changes")
    private Boolean moodChanges;

    @Column(name = "sleeping_difficulties")
    private Boolean sleepingDifficulties;

    @Column(name = "weight_gain")
    private Boolean aumentoDePeso;

    @Column(name = "loss_of_bone_density")
    private Boolean lossOfBoneDensity;

    @Column(name = "changes_in_libido")
    private Boolean changesInLibido;

    public QuestionsUserMenopause() {
    }
}
