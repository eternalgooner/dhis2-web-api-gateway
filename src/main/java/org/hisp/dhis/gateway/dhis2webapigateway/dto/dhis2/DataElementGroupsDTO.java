package org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2;

import lombok.Data;
import org.hisp.dhis.gateway.dhis2webapigateway.domain.DataElementGroup;

import java.util.List;

@Data
public class DataElementGroupsDTO {
    private List<DataElementGroup> dataElementGroups;
}
