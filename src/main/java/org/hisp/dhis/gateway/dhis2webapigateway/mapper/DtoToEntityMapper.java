package org.hisp.dhis.gateway.dhis2webapigateway.mapper;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper class using functions to map DTOs to Entities
 */
@Component
public class DtoToEntityMapper {

    // Data Elements
    public Function<DataElementDTO, DataElement> dataElementDtoToEntity = dataElementDTO -> DataElement.builder()
            .id(dataElementDTO.getId())
            .displayName(dataElementDTO.getDisplayName())
            .dataElementGroups(dataElementDTO.getDataElementGroups().stream()
                    .map(deg -> DataElementGroup.builder().id(deg.getId()).build())
                    .collect(Collectors.toSet())).build();

    public Function<DataElementsDTO, List<DataElement>> dataElementsDtoToEntities = dataElementsDTO ->
            dataElementsDTO.getDataElements().stream()
                    .map(dataElementDtoToEntity).collect(Collectors.toList());

    // Data Element Groups
    public Function<DataElementGroupDTO, DataElementGroup> dataElementGroupDtoToEntity = dataElementGroupDTO ->
            DataElementGroup.builder()
                    .id(dataElementGroupDTO.getId())
                    .displayName(dataElementGroupDTO.getDisplayName())
                    .dataElements(dataElementGroupDTO.getDataElements().stream()
                            .map(de -> DataElement.builder().id(de.getId()).build())
                            .collect(Collectors.toSet())).build();

    public Function<DataElementGroupsDTO, List<DataElementGroup>> dataElementGroupsDtoToEntities = dataElementGroupsDTO ->
            dataElementGroupsDTO.getDataElementGroups().stream()
                    .map(dataElementGroupDtoToEntity).collect(Collectors.toList());
}
