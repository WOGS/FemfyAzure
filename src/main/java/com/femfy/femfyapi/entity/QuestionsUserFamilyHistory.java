package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

@Table(name="questions_user_family_history")
public class QuestionsUserFamilyHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "breast_cancer")
    private boolean breastCancer;

    @Column(name = "ovarian_cancer")
    private boolean ovarianCancer;

    @Column(name = "endometriosis")
    private boolean endometriosis;

    @Column(name = "uterine_fibroids")
    private boolean uterineFibroids;

    @Column(name = "sop")
    private boolean sop;

    @Column(name = "early_menopause")
    private boolean earlyMenopause;

    public QuestionsUserFamilyHistory(){

    }


}
