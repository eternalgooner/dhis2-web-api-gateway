package org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup;

import lombok.Data;

import java.util.List;

@Data
public class DataElementGroupsDTO {
    private List<DataElementGroupDTO> dataElementGroups;
}