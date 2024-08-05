package com.Mattheo92.ClinicProxyApp.integration;

import com.Mattheo92.ClinicProxyApp.client.ProxyClient;
import com.Mattheo92.ClinicProxyApp.handler.exception.DoctorNotFoundException;
import com.Mattheo92.ClinicProxyApp.model.Doctor;
import com.Mattheo92.ClinicProxyApp.model.Visit;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import feign.FeignException;
import feign.RetryableException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProxyClientIntegrationTest {
    private static WireMockServer wireMockServer;

    @Autowired
    private ProxyClient proxyClient;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(8085);
        WireMock.configureFor("localhost", 8085);
        wireMockServer.start();
    }

    @AfterAll
    public static void end() {
        wireMockServer.stop();
    }

    @Test
    public void getVisitsForDoctor_Status200() throws Exception {
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
        wireMockServer.stubFor(get(urlEqualTo("/visits/doctor/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(objectMapper.writeValueAsString(expectedVisitDtoList))));

        List<VisitDto> result = proxyClient.getVisitsForDoctor(doctorId);

        assertEquals(expectedVisitDtoList.size(), result.size());
        assertEquals(expectedVisitDtoList.get(0).getStartDate(), result.get(0).getStartDate());
        assertEquals(expectedVisitDtoList.get(1).getStartDate(), result.get(1).getStartDate());
    }

    @Test
    public void getVisitsForDoctor_Status404() throws Exception {
        wireMockServer.stubFor(get(urlEqualTo("/visits/doctor/999"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Doctor not found\"}")));

        FeignException exception = assertThrows(FeignException.NotFound.class, () -> {
            proxyClient.getVisitsForDoctor(999L);
        });

        assertTrue(exception.getMessage().contains("404"));
    }

    @Test
    public void GetVisitsForDoctor_Status503() throws Exception {
        wireMockServer.stubFor(get(urlEqualTo("/visits/doctor/1"))
                .willReturn(aResponse()
                        .withStatus(503)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Service is unavailable\"}")));

        RetryableException exception = assertThrows(RetryableException.class, () -> {
            proxyClient.getVisitsForDoctor(1L);
        });

        assertEquals("Service is unavailable", exception.getMessage());
        wireMockServer.verify(4, getRequestedFor(urlEqualTo("/visits/doctor/1")));
    }
}