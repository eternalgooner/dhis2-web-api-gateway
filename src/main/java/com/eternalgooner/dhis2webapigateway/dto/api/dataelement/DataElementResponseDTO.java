package com.eternalgooner.dhis2webapigateway.dto.api.dataelement;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DataElementResponseDTO {
    private String id;
    private String name;
    private Set<String> groups;
}
