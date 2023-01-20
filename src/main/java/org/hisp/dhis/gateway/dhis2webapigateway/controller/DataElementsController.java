package org.hisp.dhis.gateway.dhis2webapigateway.controller;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.DataElementsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hisp.dhis.gateway.dhis2webapigateway.config.CacheConfig.DATA_ELEMENTS_CACHE_KEY;

@RestController
public class DataElementsController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/api/dataElements")
    public DataElementsResponseDTO getDataElements() {
        System.out.println("received call for data elements, get from cache");
        return (DataElementsResponseDTO) cacheManager.getCache(DATA_ELEMENTS_CACHE_KEY);
    }
}
