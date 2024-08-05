package com.Mattheo92.ClinicProxyApp.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class VisitDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}