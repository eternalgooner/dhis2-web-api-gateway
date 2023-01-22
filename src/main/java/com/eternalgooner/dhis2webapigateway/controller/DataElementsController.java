package com.eternalgooner.dhis2webapigateway.controller;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.service.Dhis2DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.eternalgooner.dhis2webapigateway.config.CacheConfig.DATA_ELEMENTS_CACHE;
import static com.eternalgooner.dhis2webapigateway.config.CacheConfig.DATA_ELEMENT_GROUPS_CACHE;

/**
 * REST Controller exposing 2 endpoints
 * Both endpoints use caching to:
 *   (i) reduce unnecessary calls to the DHIS2 instance
 *   (ii) provide quick responses to client requests
 */
@RestController
@RequestMapping(path = "/api")
@Slf4j
public class DataElementsController {

    @Autowired private Dhis2DataService dhis2DataService;

    @Cacheable(DATA_ELEMENTS_CACHE)
    @GetMapping("/dataElements")
    public DataElementsResponseDTO getDataElements() {
        log.info("received call for data elements, get from cache or DB if required");
        return dhis2DataService.getDataElements();
    }

    @Cacheable(DATA_ELEMENT_GROUPS_CACHE)
    @GetMapping("/dataElementGroups")
    public DataElementGroupsResponseDTO getDataElementGroups() {
        log.info("received call for data element groups, get from cache or DB if required");
        return dhis2DataService.getDataElementGroups();
    }

}
