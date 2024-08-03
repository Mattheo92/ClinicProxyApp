package com.Mattheo92.ClinicProxyApp.model.mapper;

@Component
@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitDto visitDto(Visit source);
    Visit destinationToSource (VisitDto destination);
    List<VisitDto> ListDto (List<Visit> visits);
}