package org.hisp.dhis.gateway.dhis2webapigateway.service;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.DataElementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;

@Service
public class Dhis2WebApi {

    @Autowired
    private WebClient webClient;

    @Value("${dhis2.data.elements.url.path}")
    private String dhis2DataElementsPath;

    @Value("${dhis2.data.element.groups.url.path}")
    private String dhis2DataElementGroupsPath;

    @PostConstruct
    public DataElementsDTO getDataElements() {
        System.out.println("making dhis2 call for data elements...");
        DataElementsDTO result = webClient.get()
                .uri(dhis2DataElementsPath)
                .retrieve()
                .bodyToMono(DataElementsDTO.class)
                .block();

        System.out.println("response from dhis: " + result);
        return null;
    }
}
