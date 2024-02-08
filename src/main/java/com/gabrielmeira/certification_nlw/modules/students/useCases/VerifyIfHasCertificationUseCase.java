package com.gabrielmeira.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielmeira.certification_nlw.modules.students.dto.VerifyHasCertificationDto;
import com.gabrielmeira.certification_nlw.modules.students.repository.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {
    @Autowired
    private CertificationStudentRepository certificationStudantRepository;

    public boolean execute(VerifyHasCertificationDto dto) {
        var result = this.certificationStudantRepository.findByStudentEmailAndTechnology(
            dto.getEmail(), dto.getTechnology());

        if(!result.isEmpty()) {
            return true;
        }

        return false;
    }
}
