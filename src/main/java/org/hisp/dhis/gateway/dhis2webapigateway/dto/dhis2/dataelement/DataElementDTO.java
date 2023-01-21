package org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataElementDTO {
    private String id;
    private String displayName;
    private List<DataElementGroupForElementsDTO> dataElementGroups;
}
