package com.Mattheo92.ClinicProxyApp.service;

import com.Mattheo92.ClinicProxyApp.client.ProxyClient;
import com.Mattheo92.ClinicProxyApp.model.dtos.VisitDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final ProxyClient proxyClient;

   public List<VisitDto> getVisitsForDoctor (Long doctorId){
        return proxyClient.getVisitsForDoctor(doctorId);
    }

    public void cancelVisit(Long visitId){
       proxyClient.cancelVisit(visitId);
    }

}
