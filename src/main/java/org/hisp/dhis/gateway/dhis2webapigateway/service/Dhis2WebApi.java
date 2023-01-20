package org.hisp.dhis.gateway.dhis2webapigateway.service;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.DataElementGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.DataElementsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static org.hisp.dhis.gateway.dhis2webapigateway.config.CacheConfig.*;

@Service
public class Dhis2WebApi {

    @Autowired
    private WebClient webClient;

    @Value("${dhis2.data.elements.url.path}")
    private String dhis2DataElementsPath;

    @Value("${dhis2.data.element.groups.url.path}")
    private String dhis2DataElementGroupsPath;

    @EventListener(ApplicationReadyEvent.class)
    protected void initCache() {
        System.out.println("initialising cache by calling DHIS2 Web API for DataElements & DataElementGroups");
        getDataElements();
        getDataElementGroups();
    }

    @Cacheable(value = DATA_ELEMENTS_CACHE, key = DATA_ELEMENTS_CACHE_KEY)
    public DataElementsDTO getDataElements() {
        System.out.println("making dhis2 call for data elements");
        DataElementsDTO result = webClient.get()
                .uri(dhis2DataElementsPath)
                .retrieve()
                .bodyToMono(DataElementsDTO.class)
                .block();

        System.out.println("response from dhis: " + result);
        return result;
    }

    @Cacheable(value = DATA_ELEMENT_GROUPS_CACHE, key = DATA_ELEMENT_GROUPS_CACHE_KEY)
    public DataElementGroupsDTO getDataElementGroups() {
        System.out.println("making dhis2 call for data element groups");
        DataElementGroupsDTO result = webClient.get()
                .uri(dhis2DataElementGroupsPath)
                .retrieve()
                .bodyToMono(DataElementGroupsDTO.class)
                .block();

        System.out.println("response from dhis: " + result);
        return result;
    }
}
