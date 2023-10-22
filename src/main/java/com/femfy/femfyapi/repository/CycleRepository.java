package com.femfy.femfyapi.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.femfy.femfyapi.entity.Cycle;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Long> {

    @Transactional
    public Cycle findByIdUserAndDateBeging(Long idUser, Date dateBeging);

    @Transactional
    public List<Cycle> findAllByIdUser(Long idUser);

}
