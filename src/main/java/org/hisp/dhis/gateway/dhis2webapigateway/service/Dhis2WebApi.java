package org.hisp.dhis.gateway.dhis2webapigateway.service;

import lombok.extern.slf4j.Slf4j;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class Dhis2WebApi {

    @Autowired
    private WebClient webClient;

    @Value("${dhis2.data.elements.url.path}")
    private String dhis2DataElementsPath;

    @Value("${dhis2.data.element.groups.url.path}")
    private String dhis2DataElementGroupsPath;

//    @PostConstruct
//    protected void initCache() {
//        System.out.println("initialising cache by calling DHIS2 Web API for DataElements & DataElementGroups");
//        getDataElements();
//        getDataElementGroups();
//    }

//    @Cacheable(value = DATA_ELEMENTS_CACHE, key = DATA_ELEMENTS_CACHE_KEY)
    public DataElementsDTO getDataElements() {
        log.info("making dhis2 call for data elements");
        DataElementsDTO result = webClient.get()
                .uri(dhis2DataElementsPath)
                .retrieve()
                .bodyToMono(DataElementsDTO.class)
                .block();

//        log.info("response from dhis: " + result);

        //TODO convert dto to entities for saving

        return result;
    }

//    @Cacheable(value = DATA_ELEMENT_GROUPS_CACHE, key = DATA_ELEMENT_GROUPS_CACHE_KEY)
    public DataElementGroupsDTO getDataElementGroups() {
        log.info("making dhis2 call for data element groups");
        DataElementGroupsDTO result = webClient.get()
                .uri(dhis2DataElementGroupsPath)
                .retrieve()
                .bodyToMono(DataElementGroupsDTO.class)
                .block();

//        log.info("response from dhis: " + result);
        return result;
    }
}
