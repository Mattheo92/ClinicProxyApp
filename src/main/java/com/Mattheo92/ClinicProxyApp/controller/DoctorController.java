package com.Mattheo92.ClinicProxyApp.controller;

import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import com.Mattheo92.ClinicProxyApp.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    @Operation(summary = "Get visits for a doctor", description = "Returned list of visits associated with a doctor with given id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned list of visits", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VisitDto.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{doctorId}")
    public List<VisitDto> getVisitsForDoctor(
            @PathVariable("doctorId") Long doctorId) {
        return doctorService.getVisitsForDoctor(doctorId);
    }

    @Operation(summary = "Cancel a visit", description = "Cancels a visit by its ID and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visit successfully cancelled", content = @Content(mediaType = "text/plain")),
            @ApiResponse(responseCode = "404", description = "Visit not found", content = @Content)
    })
    @DeleteMapping("/visits/{visitId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelVisit(@PathVariable Long visitId) {
        doctorService.cancelVisit(visitId);}
}