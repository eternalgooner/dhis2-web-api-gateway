package com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DataElementGroupsResponseDTO {

    @JsonValue
    private Set<DataElementGroupResponseDTO> members;
}
