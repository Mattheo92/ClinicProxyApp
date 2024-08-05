package com.Mattheo92.ClinicProxyApp.model.mapper;

import com.Mattheo92.ClinicProxyApp.model.Patient;
import com.Mattheo92.ClinicProxyApp.model.dtos.PatientDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto dto(Patient patient);

    List<PatientDto> toDtos(List<Patient> patients);

    Patient dto(PatientDto patientDto);
}