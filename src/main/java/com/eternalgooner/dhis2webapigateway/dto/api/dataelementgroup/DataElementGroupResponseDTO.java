package com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DataElementGroupResponseDTO {
    private String id;
    private String name;
    private Set<String> members;
}
