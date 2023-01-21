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

@Component
public class DtoToEntityMapper {

    // dto -> entity
    // data elements
    public Function<DataElementDTO, DataElement> toDeEntity = dataElementDTO -> DataElement.builder()
            .id(dataElementDTO.getId())
            .displayName(dataElementDTO.getDisplayName())
            .dataElementGroups(dataElementDTO.getDataElementGroups().stream()
                    .map(deg -> DataElementGroup.builder().id(deg.getId()).build())
                    .collect(Collectors.toSet())).build();

    public Function<DataElementsDTO, List<DataElement>> dhis2DataElementsDtoToEntities = dataElementsDTO -> {
        //get all data els
        final List<DataElementDTO> dataElements = dataElementsDTO.getDataElements();
        return dataElements.stream().map(toDeEntity).collect(Collectors.toList());
    };

    // data element groups dto
    public Function<DataElementGroupDTO, DataElementGroup> toDegEntity = dataElementGroupDTO -> DataElementGroup.builder()
            .id(dataElementGroupDTO.getId())
            .displayName(dataElementGroupDTO.getDisplayName())
            .dataElements(dataElementGroupDTO.getDataElements().stream()
                    .map(de -> DataElement.builder().id(de.getId()).build())
                    .collect(Collectors.toSet())).build();

    public Function<DataElementGroupsDTO, List<DataElementGroup>> dhis2DataElementGroupsDtoToEntities = dataElementGroupsDTO -> {
        //get all data el groups
        final List<DataElementGroupDTO> dataElementGroups = dataElementGroupsDTO.getDataElementGroups();
        return dataElementGroups.stream().map(toDegEntity).collect(Collectors.toList());
    };
}
