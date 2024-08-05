package com.Mattheo92.ClinicProxyApp.service;

import com.Mattheo92.ClinicProxyApp.client.ProxyClient;
import com.Mattheo92.ClinicProxyApp.handler.exception.DoctorNotFoundException;
import com.Mattheo92.ClinicProxyApp.handler.exception.VisitNotFoundException;
import com.Mattheo92.ClinicProxyApp.model.Doctor;
import com.Mattheo92.ClinicProxyApp.model.Visit;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    @Mock
    private ProxyClient proxyClient;

    private DoctorService doctorService;

    @BeforeEach
    public void setup() {
        this.doctorService = new DoctorService(proxyClient);
    }

    @Test
    public void getVisitsForDoctor_PositiveCase() {
        Long doctorId = 1L;
        LocalDate start = LocalDate.of(2024, 12, 12);
        LocalDate end = LocalDate.of(2025, 12, 12);
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        Visit visit1 = new Visit();
        visit1.setId(1L);
        visit1.setStartDate(LocalDateTime.of(2025, 7, 1, 10, 0));
        visit1.setEndDate(LocalDateTime.of(2025, 7, 1, 11, 0));
        Visit visit2 = new Visit();
        visit2.setId(2L);
        visit2.setStartDate(LocalDateTime.of(2025, 7, 2, 14, 0));
        visit2.setEndDate(LocalDateTime.of(2025, 7, 2, 15, 0));
        List<Visit> doctorVisits = List.of(visit1, visit2);
        doctor.setVisits(doctorVisits);
        VisitDto visitDto1 = new VisitDto(visit1.getStartDate(), visit1.getEndDate());
        VisitDto visitDto2 = new VisitDto(visit2.getStartDate(), visit2.getEndDate());
        List<VisitDto> expectedVisitDtoList = List.of(visitDto1, visitDto2);

        when(proxyClient.getVisitsForDoctor(doctorId)).thenReturn(expectedVisitDtoList);

        List<VisitDto> result = doctorService.getVisitsForDoctor(doctorId);

        assertEquals(expectedVisitDtoList.size(), result.size());
        assertEquals(expectedVisitDtoList.get(0).getStartDate(), result.get(0).getStartDate());
        assertEquals(expectedVisitDtoList.get(1).getStartDate(), result.get(1).getStartDate());
    }

    @Test
    public void getVisitsForDoctor_DoctorNotFound() {
        Long doctorId = 1L;

        when(proxyClient.getVisitsForDoctor(doctorId)).thenThrow(DoctorNotFoundException.class);

        assertThrows(DoctorNotFoundException.class, () -> {
            doctorService.getVisitsForDoctor(doctorId);
        });
    }

    @Test
    public void cancelVisit_PositiveCase() {
        Visit visit1 = new Visit();
        visit1.setId(1L);
        visit1.setStartDate(LocalDateTime.of(2025, 7, 1, 10, 0));
        visit1.setEndDate(LocalDateTime.of(2025, 7, 1, 11, 0));

        doctorService.cancelVisit(1L);

        verify(proxyClient, times(1)).cancelVisit(1L);
    }

    @Test
    public void cancelVisit_VisitNotFound() {
        Long visitId = 1L;
        doThrow(VisitNotFoundException.class).when(proxyClient).cancelVisit(visitId);

        assertThrows(VisitNotFoundException.class, () -> {
            doctorService.cancelVisit(visitId);
        });
        verify(proxyClient, times(1)).cancelVisit(1L);
    }
}