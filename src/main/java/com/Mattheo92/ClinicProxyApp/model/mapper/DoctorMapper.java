package com.Mattheo92.ClinicProxyApp.model.mapper;

import com.Mattheo92.ClinicProxyApp.model.Doctor;
import com.Mattheo92.ClinicProxyApp.model.dtos.DoctorDto;
import com.Mattheo92.ClinicProxyApp.model.dtos.SimpleDoctorDto;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDto toDto(Doctor source);
    List<DoctorDto> toDtos (List<Doctor> doctors);
    SimpleDoctorDto toSimpleDto(Doctor source);
}