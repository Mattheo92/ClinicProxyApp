package com.Mattheo92.ClinicProxyApp.model.mapper;

@Component
@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDto toDto(Doctor source);
    List<DoctorDto> toDtos (List<Doctor> doctors);
    SimpleDoctorDto toSimpleDto(Doctor source);
}