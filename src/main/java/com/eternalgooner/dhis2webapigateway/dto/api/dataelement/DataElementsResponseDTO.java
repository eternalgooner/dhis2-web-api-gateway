package com.eternalgooner.dhis2webapigateway.dto.api.dataelement;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DataElementsResponseDTO {

    @JsonValue
    private Set<DataElementResponseDTO> dataElements;
}
