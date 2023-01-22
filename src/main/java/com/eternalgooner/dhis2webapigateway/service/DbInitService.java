package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.mapper.DtoToEntityMapper;
import com.eternalgooner.dhis2webapigateway.repository.DataElementGroupRepository;
import lombok.extern.slf4j.Slf4j;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.repository.DataElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${db.init.on.startup.enabled}")
    private boolean initDbOnStartup;

    @PostConstruct
    public void initDb() {
        if(initDbOnStartup) {
            getDhis2DataAndLoadIntoDb();
        }
    }

    public void getDhis2DataAndLoadIntoDb() {
        log.info("making calls to DHIS2 for data");
        final DataElementsDTO dataElements = dhis2WebApiService.getDataElements();
        final DataElementGroupsDTO dataElementGroups = dhis2WebApiService.getDataElementGroups();

        log.info("converting DHIS2 web responses to entities");
        final List<DataElement> dataElementEntities = dtoToEntityMapper.dataElementsDtoToEntities.apply(dataElements);
        final List<DataElementGroup> dataElementGroupEntities = dtoToEntityMapper.dataElementGroupsDtoToEntities.apply(dataElementGroups);

        log.info("persisting DHIS2 data");
        dataElementRepository.saveAll(dataElementEntities);
        dataElementGroupRepository.saveAll(dataElementGroupEntities);
    }
}
