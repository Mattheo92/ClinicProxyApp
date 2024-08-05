package com.Mattheo92.ClinicProxyApp.fallback;

import com.Mattheo92.ClinicProxyApp.client.ProxyClient;
import com.Mattheo92.ClinicProxyApp.handler.exception.DoctorNotFoundException;
import com.Mattheo92.ClinicProxyApp.handler.exception.ProxyClientException;
import com.Mattheo92.ClinicProxyApp.handler.exception.VisitNotFoundException;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ProxyClientFallback implements ProxyClient {


    @Override
    public List<VisitDto> getVisitsForDoctor(Long doctorId) {
        throw new VisitNotFoundException("Visit not found");
    }

    @Override
    public void cancelVisit(Long visitId) {
        throw new RuntimeException("Sorry, but you can't cancel this visit");
    }

    @Override
    public List<VisitDto> getAvailableVisitsByDoctorId(Long doctorId) {
       throw new DoctorNotFoundException("Doctor not found");
    }

    @Override
    public List<VisitDto> getVisitsByDoctorSpecializationAndStartDateBetween(String specialization, LocalDate start, LocalDate end) {
        throw new VisitNotFoundException("Visit not found");
    }

    @Override
    public List<VisitDto> getAvailableVisitsAndDateRange(LocalDate start, LocalDate end) {
        throw new VisitNotFoundException("Visit not found");
    }
}
