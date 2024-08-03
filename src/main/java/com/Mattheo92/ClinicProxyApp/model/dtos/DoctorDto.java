package com.Mattheo92.ClinicProxyApp.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DoctorDto {
 
    private String name;
    private String surname;
    private String specialization;
    private String email;
}