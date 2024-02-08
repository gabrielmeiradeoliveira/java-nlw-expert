package com.gabrielmeira.certification_nlw.modules.questions.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielmeira.certification_nlw.modules.questions.dto.AlternativesResultDto;
import com.gabrielmeira.certification_nlw.modules.questions.dto.QuestionResultDto;
import com.gabrielmeira.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.gabrielmeira.certification_nlw.modules.questions.entities.QuestionEntity;
import com.gabrielmeira.certification_nlw.modules.questions.repository.QuestionsRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionsRepository questionsRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDto> findByTechnology(@PathVariable String technology) {
        var result = questionsRepository.findByTechnology(technology);
        var toMap = result.stream().map(question -> mapQuestionToDTO(question))
        .collect(Collectors.toList());

        return toMap;
    }

    static QuestionResultDto mapQuestionToDTO(QuestionEntity question) {
        var questionResultDto = QuestionResultDto.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription()).build();

        List<AlternativesResultDto> alternativesResultDTOs = question.getAlternatives()
                .stream().map(alternative -> mapAlternativeDTO(alternative))
                .collect(Collectors.toList());

        questionResultDto.setAlternatives(alternativesResultDTOs);
        return questionResultDto;
    }

    static AlternativesResultDto mapAlternativeDTO(AlternativesEntity alternativesResultDTO) {
        return AlternativesResultDto.builder()
                .id(alternativesResultDTO.getId())
                .description(alternativesResultDTO.getDescription()).build();
    }
}
