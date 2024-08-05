package com.Mattheo92.ClinicProxyApp.service;

import com.Mattheo92.ClinicProxyApp.client.ProxyClient;
import com.Mattheo92.ClinicProxyApp.handler.exception.DoctorNotFoundException;
import com.Mattheo92.ClinicProxyApp.handler.exception.VisitNotFoundException;
import com.Mattheo92.ClinicProxyApp.model.Doctor;
import com.Mattheo92.ClinicProxyApp.model.Visit;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import com.Mattheo92.ClinicProxyApp.model.mapper.VisitMapper;
import feign.FeignException;
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
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private ProxyClient proxyClient;

    private PatientService patientService;

    @BeforeEach
    public void setup() {
        this.patientService = new PatientService(proxyClient);
    }

    @Test
    public void getAvailableVisitsByDoctorId_DoctorAndVisitsExists_PositiveCase() {
        Long doctorId = 1L;
        Visit visit1 = new Visit();
        visit1.setId(1L);
        visit1.setStartDate(LocalDateTime.of(2025, 7, 1, 10, 0));
        visit1.setEndDate(LocalDateTime.of(2025, 7, 1, 11, 0));
        Visit visit2 = new Visit();
        visit2.setId(2L);
        visit2.setStartDate(LocalDateTime.of(2025, 7, 2, 14, 0));
        visit2.setEndDate(LocalDateTime.of(2025, 7, 2, 15, 0));
        VisitDto visitDto1 = new VisitDto(visit1.getStartDate(), visit1.getEndDate());
        VisitDto visitDto2 = new VisitDto(visit2.getStartDate(), visit2.getEndDate());
        List<VisitDto> expectedVisitDtoList = List.of(visitDto1, visitDto2);

        when(proxyClient.getAvailableVisitsByDoctorId(doctorId)).thenReturn(expectedVisitDtoList);

        List<VisitDto> result = patientService.getAvailableVisitsByDoctorId(doctorId);

        assertEquals(expectedVisitDtoList.size(), result.size());
        assertEquals(expectedVisitDtoList.get(0).getStartDate(), result.get(0).getStartDate());
        assertEquals(expectedVisitDtoList.get(1).getStartDate(), result.get(1).getStartDate());
    }
    @Test
    public void getAvailableVisitsByDoctorId_DoctorNotExists_NegativeCase(){
        Long doctorId = 1L;
        when(proxyClient.getAvailableVisitsByDoctorId(doctorId)).thenThrow(DoctorNotFoundException.class);

        assertThrows(DoctorNotFoundException.class, () -> {
            patientService.getAvailableVisitsByDoctorId(doctorId);
        });
    }

    @Test
    public void getVisitsByDoctorSpecializationAndStartDateBetween_PositiveCase(){
        String doctorSpecialization = "anestezjolog";
        LocalDate start = LocalDate.of(2024,12, 12);
        LocalDate end = LocalDate.of(2025,12, 12);

        Visit visit1 = new Visit();
        visit1.setId(1L);
        visit1.setStartDate(LocalDateTime.of(2025, 7, 1, 10, 0));
        visit1.setEndDate(LocalDateTime.of(2025, 7, 1, 11, 0));
        Visit visit2 = new Visit();
        visit2.setId(2L);
        visit2.setStartDate(LocalDateTime.of(2025, 7, 2, 14, 0));
        visit2.setEndDate(LocalDateTime.of(2025, 7, 2, 15, 0));
        List<Visit> doctorVisits = List.of(visit1, visit2);
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setSpecialization(doctorSpecialization);
        doctor.setVisits(doctorVisits);
        VisitDto visitDto1 = new VisitDto(visit1.getStartDate(), visit1.getEndDate());
        VisitDto visitDto2 = new VisitDto(visit2.getStartDate(), visit2.getEndDate());
        List<VisitDto> expectedVisitDtoList = List.of(visitDto1, visitDto2);

        when(proxyClient.getVisitsByDoctorSpecializationAndStartDateBetween(doctorSpecialization, start, end)).thenReturn(expectedVisitDtoList);

        List<VisitDto> result = patientService.getVisitsByDoctorSpecializationAndStartDateBetween(doctorSpecialization, start, end);

        assertEquals(expectedVisitDtoList.size(), result.size());
        assertEquals(expectedVisitDtoList.get(0).getStartDate(), result.get(0).getStartDate());
        assertEquals(expectedVisitDtoList.get(1).getStartDate(), result.get(1).getStartDate());
    }
@Test
    public void getVisitsByDoctorSpecializationAndStartDateBetween_DoctorNotFound(){
    String doctorSpecialization = "anestezjolog";
    LocalDate start = LocalDate.of(2024,12, 12);
    LocalDate end = LocalDate.of(2025,12, 12);

    when(proxyClient.getVisitsByDoctorSpecializationAndStartDateBetween(doctorSpecialization, start, end)).thenThrow(DoctorNotFoundException.class);
    assertThrows(DoctorNotFoundException.class, () -> {
        patientService.getVisitsByDoctorSpecializationAndStartDateBetween(doctorSpecialization, start, end);
    });
}
@Test
    public void getAvailableVisitsAndDateRange_PositiveCase(){
    LocalDate start = LocalDate.of(2024,12, 12);
    LocalDate end = LocalDate.of(2025,12, 12);

    Visit visit1 = new Visit();
    visit1.setId(1L);
    visit1.setStartDate(LocalDateTime.of(2025, 7, 1, 10, 0));
    visit1.setEndDate(LocalDateTime.of(2025, 7, 1, 11, 0));
    Visit visit2 = new Visit();
    visit2.setId(2L);
    visit2.setStartDate(LocalDateTime.of(2025, 7, 2, 14, 0));
    visit2.setEndDate(LocalDateTime.of(2025, 7, 2, 15, 0));
    List<Visit> doctorVisits = List.of(visit1, visit2);
    VisitDto visitDto1 = new VisitDto(visit1.getStartDate(), visit1.getEndDate());
    VisitDto visitDto2 = new VisitDto(visit2.getStartDate(), visit2.getEndDate());
    List<VisitDto> expectedVisitDtoList = List.of(visitDto1, visitDto2);

    when(proxyClient.getAvailableVisitsAndDateRange(start, end)).thenReturn(expectedVisitDtoList);

    List<VisitDto> result = patientService.getAvailableVisitsAndDateRange(start, end);
    assertEquals(expectedVisitDtoList.size(), result.size());
    assertEquals(expectedVisitDtoList.get(0).getStartDate(), result.get(0).getStartDate());
    assertEquals(expectedVisitDtoList.get(1).getStartDate(), result.get(1).getStartDate());
}

@Test
    public void getAvailableVisitsAndDateRange_VisitsNotFound(){
    LocalDate start = LocalDate.of(2024,12, 12);
    LocalDate end = LocalDate.of(2025,12, 12);

    when(proxyClient.getAvailableVisitsAndDateRange(start, end)).thenThrow(VisitNotFoundException.class);
    assertThrows(VisitNotFoundException.class, () -> {
        patientService.getAvailableVisitsAndDateRange(start, end);
    });
}
}