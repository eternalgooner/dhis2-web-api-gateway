package org.hisp.dhis.gateway.dhis2webapigateway.service;

import lombok.extern.slf4j.Slf4j;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
import org.hisp.dhis.gateway.dhis2webapigateway.mapper.DtoToEntityMapper;
import org.hisp.dhis.gateway.dhis2webapigateway.mapper.EntityToDtoMapper;
import org.hisp.dhis.gateway.dhis2webapigateway.repository.DataElementGroupRepository;
import org.hisp.dhis.gateway.dhis2webapigateway.repository.DataElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class DataService {

    @Autowired private Dhis2WebApi dhis2WebApi;

    @Autowired private DataElementRepository dataElementRepository;
    @Autowired private DataElementGroupRepository dataElementGroupRepository;

    @Autowired DtoToEntityMapper dtoToEntityMapper;
    @Autowired EntityToDtoMapper entityToDtoMapper;

    public DataElementsResponseDTO getData() {
        final DataElementsDTO dataElements = dhis2WebApi.getDataElements();
        final DataElementGroupsDTO dataElementGroups = dhis2WebApi.getDataElementGroups();

        //convert to entity
        final List<DataElement> dataElementEntities = dtoToEntityMapper.dhis2DataElementsDtoToEntities.apply(dataElements);
        final List<DataElementGroup> dataElementGroupEntities = dtoToEntityMapper.dhis2DataElementGroupsDtoToEntities.apply(dataElementGroups);

        //save to DB
        dataElementRepository.saveAll(dataElementEntities);
        dataElementGroupRepository.saveAll(dataElementGroupEntities);

        //get data in format to return dto
        final List<DataElement> all = StreamSupport.stream(dataElementRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        //return
        return entityToDtoMapper.dataElEntitiesToDto.apply(all);
    }
}
