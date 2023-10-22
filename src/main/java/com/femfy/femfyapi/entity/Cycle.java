package com.femfy.femfyapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "cycle")
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUser;


    private Date dateBeging;


    private Date dateEnd;

    private String status;

    private Integer daysOfBleeding;

    public Cycle() {
    }
}