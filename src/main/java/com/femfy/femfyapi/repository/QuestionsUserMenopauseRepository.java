package com.femfy.femfyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.femfy.femfyapi.entity.QuestionsUserMenopause;

@Repository
public interface QuestionsUserMenopauseRepository extends JpaRepository<QuestionsUserMenopause, Long> {

}