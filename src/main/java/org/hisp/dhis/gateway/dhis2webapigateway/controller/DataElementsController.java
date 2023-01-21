package org.hisp.dhis.gateway.dhis2webapigateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hisp.dhis.gateway.dhis2webapigateway.config.CacheConfig.DATA_ELEMENTS_CACHE;

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class DataElementsController {

    @Autowired private DataService dataService;

    @Cacheable(DATA_ELEMENTS_CACHE)
    @GetMapping("/dataElements")
    public DataElementsResponseDTO getDataElements() {
        log.info("received call for data elements, get from cache or DHIS2 if required");
        return dataService.getData();
    }
}
