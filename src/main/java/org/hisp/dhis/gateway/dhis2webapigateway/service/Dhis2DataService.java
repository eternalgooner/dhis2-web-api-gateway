package org.hisp.dhis.gateway.dhis2webapigateway.service;

import lombok.extern.slf4j.Slf4j;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
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
public class Dhis2DataService {

    @Autowired
    private DataElementRepository dataElementRepository;

    @Autowired
    private DataElementGroupRepository dataElementGroupRepository;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    public DataElementsResponseDTO getDataElements() {
        //get data in format to return dto
        final List<DataElement> all = StreamSupport.stream(dataElementRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return entityToDtoMapper.dataElEntitiesToDto.apply(all);
    }

    public DataElementGroupsResponseDTO getDataElementGroups() {
        //get data in format to return dto
        final List<DataElementGroup> all = StreamSupport.stream(dataElementGroupRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return entityToDtoMapper.dataElGroupEntitiesToDto.apply(all);
    }
}
