package org.hisp.dhis.gateway.dhis2webapigateway.mapper;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
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
    public Function<DataElement, DataElementResponseDTO> toDeRespDto = dataElementEntity ->
            DataElementResponseDTO.builder()
                    .id(dataElementEntity.getId())
                    .name(dataElementEntity.getDisplayName())
                    .groups(dataElementEntity.getDataElementGroups().stream()
                            .map(DataElementGroup::getDisplayName)
                            .collect(Collectors.toSet())).build();

    public Function<List<DataElement>, DataElementsResponseDTO> dataElEntitiesToDto = dataElEntities ->
            DataElementsResponseDTO.builder()
                    .groups(dataElEntities.stream()
                            .map(toDeRespDto).collect(Collectors.toSet())).build();


    // Data Element Groups functions
    public Function<DataElementGroup, DataElementGroupResponseDTO> toDegRespDto = dataElementGroupEntity ->
            DataElementGroupResponseDTO.builder()
                    .id(dataElementGroupEntity.getId())
                    .name(dataElementGroupEntity.getDisplayName())
                    .members(dataElementGroupEntity.getDataElements().stream()
                            .map(DataElement::getDisplayName)
                            .collect(Collectors.toSet())).build();

    public Function<List<DataElementGroup>, DataElementGroupsResponseDTO> dataElGroupEntitiesToDto = dataElGroupEntities ->
            DataElementGroupsResponseDTO.builder()
                    .members(dataElGroupEntities.stream().map(toDegRespDto).collect(Collectors.toSet())).build();

}
