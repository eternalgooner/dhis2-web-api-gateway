package org.hisp.dhis.gateway.dhis2webapigateway.mapper;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EntityToDtoMapper {

    public Function<DataElement, DataElementResponseDTO> toDeRespDto = dataElementEntity -> DataElementResponseDTO.builder()
            .id(dataElementEntity.getId())
            .name(dataElementEntity.getDisplayName())
            .groups(dataElementEntity.getDataElementGroups().stream()
                    .map(DataElementGroup::getDisplayName)
                    .collect(Collectors.toSet())).build();

    public Function<List<DataElement>, DataElementsResponseDTO> dataElEntitiesToDto = dataElEntities -> {
        //get all data els
        return DataElementsResponseDTO.builder()
                .groups(dataElEntities.stream().map(toDeRespDto).collect(Collectors.toSet())).build();
    };

}
