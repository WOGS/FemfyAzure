package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.femfy.femfyapi.entity.TypeStudy;

@Repository
public interface TypeStudyRepository extends JpaRepository<TypeStudy, Long> {

}
