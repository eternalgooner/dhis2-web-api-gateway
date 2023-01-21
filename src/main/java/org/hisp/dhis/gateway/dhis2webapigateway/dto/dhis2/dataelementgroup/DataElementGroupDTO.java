package org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup;

import lombok.Data;

import java.util.List;

@Data
public class DataElementGroupDTO {
    private String id;
    private String displayName;
    private List<DataElementForGroupsDTO> dataElements;
}
