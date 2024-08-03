package com.Mattheo92.ClinicProxyApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Long id;
    private String name;
    private String surname;
    private String specialization;
    private String email;
    private String password;
}