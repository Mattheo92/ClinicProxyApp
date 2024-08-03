package com.Mattheo92.ClinicProxyApp.model.mapper;

@Component
@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientDto dto(Patient patient);

    List<PatientDto> toDtos(List<Patient> patients);

    Patient dto(PatientDto patientDto);
}