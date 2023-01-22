package com.eternalgooner.dhis2webapigateway.mapper;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mapper class using functions to map Entities to DTOs
 */
@Component
public class EntityToDtoMapper {

    // Data Elements functions
    public Function<DataElement, DataElementResponseDTO> dataElEntityToDataElRespDto = dataElementEntity ->
            DataElementResponseDTO.builder()
                    .id(dataElementEntity.getId())
                    .name(dataElementEntity.getDisplayName())
                    .groups(dataElementEntity.getDataElementGroups().stream()
                            .map(DataElementGroup::getDisplayName)
                            .collect(Collectors.toSet())).build();

    public Function<List<DataElement>, DataElementsResponseDTO> dataElEntitiesToDto = dataElEntities ->
            DataElementsResponseDTO.builder()
                    .dataElements(dataElEntities.stream()
                            .map(dataElEntityToDataElRespDto).collect(Collectors.toSet())).build();


    // Data Element Groups functions
    public Function<DataElementGroup, DataElementGroupResponseDTO> dataElGroupEntityToDataElGroupRespDto = dataElementGroupEntity ->
            DataElementGroupResponseDTO.builder()
                    .id(dataElementGroupEntity.getId())
                    .name(dataElementGroupEntity.getDisplayName())
                    .members(dataElementGroupEntity.getDataElements().stream()
                            .map(DataElement::getDisplayName)
                            .collect(Collectors.toSet())).build();

    public Function<List<DataElementGroup>, DataElementGroupsResponseDTO> dataElGroupEntitiesToDto = dataElGroupEntities ->
            DataElementGroupsResponseDTO.builder()
                    .members(dataElGroupEntities.stream().map(dataElGroupEntityToDataElGroupRespDto).collect(Collectors.toSet())).build();

}
