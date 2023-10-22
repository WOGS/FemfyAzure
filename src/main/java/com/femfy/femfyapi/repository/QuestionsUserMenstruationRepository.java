package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.entity.QuestionsUserMenstruation;

@Repository
public interface QuestionsUserMenstruationRepository extends JpaRepository<QuestionsUserMenstruation, Long> {

}