package org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2;

import lombok.Data;
import org.hisp.dhis.gateway.dhis2webapigateway.domain.DataElement;

import java.util.List;

@Data
public class DataElementsDTO {
    private List<DataElement> dataElements;
}
