package com.Mattheo92.ClinicProxyApp.controller;

import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import com.Mattheo92.ClinicProxyApp.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "Get available visits by doctor ID", description = "Returns a list of available visits for the chosen doctor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned available visits for the doctor."),
            @ApiResponse(responseCode = "404", description = "Doctor not found.")
    })
    @GetMapping("/doctors/{doctorId}")
    public List<VisitDto> getAvailableVisitsByDoctorId(
            @PathVariable("doctorId") Long doctorId) {
        return patientService.getAvailableVisitsByDoctorId(doctorId);
    }

    @Operation(summary = "Get visits for a doctor with given specialization and a date range", description = "Returns a list of visits associated with a doctor in a given date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of visits", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitDto.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/visits")
    public List<VisitDto> getVisitsByDoctorSpecializationAndStartDateBetween(
            @RequestParam("specialization") String specialization,
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return patientService.getVisitsByDoctorSpecializationAndStartDateBetween(specialization, start, end);
    }

    @Operation(summary = "Get available visits  with given date range", description = "Returns a list of available visits in a given date range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of visits", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitDto.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("visits/range")
    public List<VisitDto> getAvailableVisitsAndDateRange(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return patientService.getAvailableVisitsAndDateRange(start, end);
    }
}
