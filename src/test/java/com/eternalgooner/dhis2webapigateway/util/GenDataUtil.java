package com.eternalgooner.dhis2webapigateway.util;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupResponseDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementGroupForElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementForGroupsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupDTO;

import java.util.List;
import java.util.Set;

import static com.eternalgooner.dhis2webapigateway.mapper.EntityToDtoMapperTest.ID_1;

public class GenDataUtil {

    public static DataElementGroupDTO genDataElementGroupDTO(String id, String name) {
        return DataElementGroupDTO.builder()
                .id(id)
                .displayName(name)
                .dataElements(List.of(genDataElementForGroupsDTO(id))).build();
    }

    public static DataElementForGroupsDTO genDataElementForGroupsDTO(String id) {
        return DataElementForGroupsDTO.builder().id(id).build();
    }

    public static DataElementDTO genDataElementDTO(String id, String name) {
        return DataElementDTO.builder()
                .id(id)
                .displayName(name)
                .dataElementGroups(List.of(genDataElementGroupsForElementsDTO(ID_1))).build();
    }

    public static DataElementGroupForElementsDTO genDataElementGroupsForElementsDTO(String id) {
        return DataElementGroupForElementsDTO.builder().id(id).build();
    }

    public static DataElementGroup genDataElementGroup(String id, String name) {
        return DataElementGroup.builder()
                .id(id)
                .displayName(name).build();
    }

    public static DataElement genDataElement(String id, String name) {
        return DataElement.builder()
                .id(id)
                .displayName(name).build();
    }

    public static DataElementResponseDTO genDataElementResponseDto(String id, String name, Set<String> groups) {
        return DataElementResponseDTO.builder()
                .id(id)
                .name(name)
                .groups(groups).build();
    }

    public static DataElementGroupResponseDTO genDataElementGroupResponseDto(String id, String name, Set<String> members) {
        return DataElementGroupResponseDTO.builder()
                .id(id)
                .name(name)
                .members(members).build();
    }
}
