package com.eternalgooner.dhis2webapigateway.dto.api.dataelement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataElementResponseDTO {
    private String id;
    private String name;
    private Set<String> groups;
}
