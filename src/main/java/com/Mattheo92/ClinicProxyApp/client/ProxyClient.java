package com.Mattheo92.ClinicProxyApp.client;

import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(name = "medicalclinic", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface ProxyClient {

    @GetMapping("visits/doctor/{doctorId}")
    List<VisitDto> getVisitsForDoctor(@PathVariable("doctorId") Long doctorId);

    @DeleteMapping("visits/{visitId}")
    void cancelVisit(@PathVariable Long visitId);

    @GetMapping("visits/doctors/{doctorId}/available-visits")
     List<VisitDto> getAvailableVisitsByDoctorId(
            @PathVariable("doctorId") Long doctorId);

    @GetMapping("visits")
    List<VisitDto> getVisitsByDoctorSpecializationAndStartDateBetween(
            @RequestParam("specialization") String specialization, @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end);

    @GetMapping("visits/range")
    public List<VisitDto> getAvailableVisitsAndDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end);
}
