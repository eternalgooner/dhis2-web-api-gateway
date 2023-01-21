package org.hisp.dhis.gateway.dhis2webapigateway.util;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementGroupForElementsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementForGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupDTO;

import java.util.List;

public class GenDataUtil {

    public static DataElementGroupDTO genDataElementGroupDTO() {
        return DataElementGroupDTO.builder()
                .id("id1")
                .displayName("name1")
                .dataElements(List.of(genDataElementForGroupsDTO())).build();
    }

    public static DataElementForGroupsDTO genDataElementForGroupsDTO() {
        return DataElementForGroupsDTO.builder().id("99").build();
    }

    public static DataElementDTO genDataElementDTO() {
        return DataElementDTO.builder()
                .id("id1")
                .displayName("name1")
                .dataElementGroups(List.of(genDataElementGroupsForElementsDTO())).build();
    }

    public static DataElementGroupForElementsDTO genDataElementGroupsForElementsDTO() {
        return DataElementGroupForElementsDTO.builder().id("12").build();
    }
}
