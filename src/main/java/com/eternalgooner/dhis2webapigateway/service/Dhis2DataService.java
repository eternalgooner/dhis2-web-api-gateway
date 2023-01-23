package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.mapper.EntityToDtoMapper;
import com.eternalgooner.dhis2webapigateway.repository.DataElementGroupRepository;
import com.eternalgooner.dhis2webapigateway.repository.DataElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Class which provides methods to get 2 types of DHIS2 data (Data Elements & Data Element Groups).
 * Both calls to DHIS use
 */
@Service
@Slf4j
public class Dhis2DataService {

    @Autowired
    private DataElementRepository dataElementRepository;

    @Autowired
    private DbInitService dbInitService;

    @Autowired
    private DataElementGroupRepository dataElementGroupRepository;

    /**
     * Method which gets all Data Elements from the DB and returns them in the API format.
     * If no data is retrieved from the DB then the DB may not have been initialised and
     * a call to initialise is triggered before trying to get the data again.
     * @return DataElementsResponseDTO which is the API format for Data Element responses
     */
    public Mono<DataElementsResponseDTO> getDataElements() {
        List<DataElement> allDataElements = getDataElementsFromDb();
        if (allDataElements.isEmpty()) {
            log.info("No Data Elements found in DB, triggering DB initialise");
            dbInitService.getDhis2DataAndLoadIntoDb();
            return Mono.just(EntityToDtoMapper.dataElEntitiesToDto.apply(getDataElementsFromDb()));
        }
        return Mono.just(EntityToDtoMapper.dataElEntitiesToDto.apply(allDataElements));
    }

    /**
     * Method which gets all Data Element Groups from the DB and returns them in the API format.
     * If no data is retrieved from the DB then the DB may not have been initialised and
     * a call to initialise is triggered before trying to get the data again.
     * @return DataElementGroupsResponseDTO which is the API format for Data Element Group responses
     */
    public Mono<DataElementGroupsResponseDTO> getDataElementGroups() {
        List<DataElementGroup> allDataElementGroups = getDataElementGroupsFromDb();
        if (allDataElementGroups.isEmpty()) {
            log.info("No Data Element Groups found in DB, triggering DB initialise");
            dbInitService.getDhis2DataAndLoadIntoDb();
            return Mono.just(EntityToDtoMapper.dataElGroupEntitiesToDto.apply(getDataElementGroupsFromDb()));
        }
        return Mono.just(EntityToDtoMapper.dataElGroupEntitiesToDto.apply(allDataElementGroups));
    }

    private List<DataElement> getDataElementsFromDb() {
        log.info("Getting all Data Elements from DB");
        return StreamSupport.stream(dataElementRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<DataElementGroup> getDataElementGroupsFromDb() {
        log.info("Getting all Data Element Groups from DB");
        return StreamSupport.stream(dataElementGroupRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
