package org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataElementGroupDTO {
    private String id;
    private String displayName;
    private List<DataElementForGroupsDTO> dataElements;
}
