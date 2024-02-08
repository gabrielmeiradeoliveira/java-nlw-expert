package com.gabrielmeira.certification_nlw.modules.students.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielmeira.certification_nlw.modules.students.dto.VerifyHasCertificationDto;
import com.gabrielmeira.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;


    @PostMapping("/verifyifhascertification")
    public String verifyIfHasCertification(
        @RequestBody VerifyHasCertificationDto verifyHasCertificationDto) {
            var result = verifyIfHasCertificationUseCase.execute(verifyHasCertificationDto);
            if(result) return "User has certification";
            return "User does not have certification";
    }
}
