package com.Mattheo92.ClinicProxyApp.model.mapper;

import com.Mattheo92.ClinicProxyApp.model.Visit;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitDto visitDto(Visit source);
    Visit destinationToSource (VisitDto destination);
    List<VisitDto> ListDto (List<Visit> visits);
}