package com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataElementGroupResponseDTO {
    private String id;
    private String name;
    private Set<String> members;
}
