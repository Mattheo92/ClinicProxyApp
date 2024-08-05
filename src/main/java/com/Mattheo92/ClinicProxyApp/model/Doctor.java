package com.Mattheo92.ClinicProxyApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Visit> visits;
}