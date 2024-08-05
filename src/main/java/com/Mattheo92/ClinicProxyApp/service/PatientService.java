package com.Mattheo92.ClinicProxyApp.service;

import com.Mattheo92.ClinicProxyApp.client.ProxyClient;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {

    private final ProxyClient proxyClient;

    public List<VisitDto> getAvailableVisitsByDoctorId(Long doctorId){
        return proxyClient.getAvailableVisitsByDoctorId(doctorId);
    }

    public List<VisitDto> getVisitsByDoctorSpecializationAndStartDateBetween(String specialization, LocalDate start, LocalDate end){
        return proxyClient.getVisitsByDoctorSpecializationAndStartDateBetween(specialization, start, end);
    }

    public List<VisitDto> getAvailableVisitsAndDateRange(LocalDate start, LocalDate end){
        return proxyClient.getAvailableVisitsAndDateRange(start, end);
    }

}
