package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import lombok.extern.slf4j.Slf4j;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service to make WEB API calls to DHIS2
 */
@Service
@Slf4j
public class Dhis2WebApiService {

    @Autowired
    private WebClient webClient;

    @Value("${dhis2.data.elements.url.path}")
    private String dhis2DataElementsPath;

    @Value("${dhis2.data.element.groups.url.path}")
    private String dhis2DataElementGroupsPath;

    public DataElementsDTO getDataElements() {
        log.info("making DHIS2 call for Data Elements");

        return webClient.get()
                .uri(dhis2DataElementsPath)
                .retrieve()
                .bodyToMono(DataElementsDTO.class)
                .block();
    }

    public DataElementGroupsDTO getDataElementGroups() {
        log.info("making DHIS2 call for Data Element Groups");

        return webClient.get()
                .uri(dhis2DataElementGroupsPath)
                .retrieve()
                .bodyToMono(DataElementGroupsDTO.class)
                .block();
    }
}
