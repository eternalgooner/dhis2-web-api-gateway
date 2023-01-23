package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.mapper.DtoToEntityMapper;
import com.eternalgooner.dhis2webapigateway.repository.DataElementGroupRepository;
import com.eternalgooner.dhis2webapigateway.repository.DataElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Consumer;

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

    @Value("${db.init.on.startup.enabled}")
    private boolean initDbOnStartup;

    /**
     * Initialise the DB with data on startup
     */
    @PostConstruct
    public void initDb() {
        if(initDbOnStartup) {
            getDhis2DataAndLoadIntoDb();
        }
    }

    public void getDhis2DataAndLoadIntoDb() {
        log.info("making calls to DHIS2 for data");
        try {
            processDataElementsWebResult.accept(dhis2WebApiService.getDataElements());
            processDataElementGroupsWebResult.accept(dhis2WebApiService.getDataElementGroups());
        } catch (Exception e) {
            log.error("Exception while calling DHIS2 {}", e.getMessage());
        }
    }

    /**
     * Consumers that process the web requests once received
     */
    private final Consumer<DataElementsDTO> processDataElementsWebResult = dataElementsDTO -> {
        log.info("converting DHIS2 Data Elements web response to entities");
        final List<DataElement> dataElementEntities = DtoToEntityMapper.dataElementsDtoToEntities.apply(dataElementsDTO);
        log.info("persisting DHIS2 Data Elements");
        dataElementRepository.saveAll(dataElementEntities);
    };

    private final Consumer<DataElementGroupsDTO> processDataElementGroupsWebResult = dataElementGroupsDTO -> {
        log.info("converting DHIS2 Data Element Groups web response to entities");
        final List<DataElementGroup> dataElementEntities = DtoToEntityMapper.dataElementGroupsDtoToEntities.apply(dataElementGroupsDTO);
        log.info("persisting DHIS2 Data Element Groups");
        dataElementGroupRepository.saveAll(dataElementEntities);
    };
}
