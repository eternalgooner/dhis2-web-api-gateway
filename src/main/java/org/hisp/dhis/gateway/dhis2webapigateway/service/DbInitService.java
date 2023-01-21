package org.hisp.dhis.gateway.dhis2webapigateway.service;

import lombok.extern.slf4j.Slf4j;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
import org.hisp.dhis.gateway.dhis2webapigateway.mapper.DtoToEntityMapper;
import org.hisp.dhis.gateway.dhis2webapigateway.repository.DataElementGroupRepository;
import org.hisp.dhis.gateway.dhis2webapigateway.repository.DataElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This Service initialises the in-memory database on startup
 * It calls 2 DHIS2 Web API endpoints and for each response it:
 *   (i) converts the response to a format for persisting
 *   (ii) persists the data
 */
@Service
@Slf4j
public class DbInitService {

    @Autowired
    private Dhis2WebApiService dhis2WebApiService;

    @Autowired
    private DataElementRepository dataElementRepository;

    @Autowired
    private DataElementGroupRepository dataElementGroupRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    @PostConstruct
    public void initDb() {
        log.info("initialising database, making calls to DHIS2 for data");
        final DataElementsDTO dataElements = dhis2WebApiService.getDataElements();
        final DataElementGroupsDTO dataElementGroups = dhis2WebApiService.getDataElementGroups();

        log.info("converting DHIS2 web responses to entities");
        final List<DataElement> dataElementEntities = dtoToEntityMapper.dhis2DataElementsDtoToEntities.apply(dataElements);
        final List<DataElementGroup> dataElementGroupEntities = dtoToEntityMapper.dhis2DataElementGroupsDtoToEntities.apply(dataElementGroups);

        log.info("persisting DHIS2 data");
        dataElementRepository.saveAll(dataElementEntities);
        dataElementGroupRepository.saveAll(dataElementGroupEntities);
    }
}
