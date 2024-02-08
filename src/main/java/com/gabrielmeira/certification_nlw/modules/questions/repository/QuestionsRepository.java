package com.gabrielmeira.certification_nlw.modules.questions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielmeira.certification_nlw.modules.questions.entities.QuestionEntity;

public interface QuestionsRepository extends JpaRepository<QuestionEntity, Long>{

    List<QuestionEntity> findByTechnology(String technology);
}
