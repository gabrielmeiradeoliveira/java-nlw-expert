package com.gabrielmeira.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyHasCertificationDto{
    private String email;
    private String technology;
}
