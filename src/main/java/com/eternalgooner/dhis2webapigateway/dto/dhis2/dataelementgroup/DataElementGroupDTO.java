package com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataElementGroupDTO {
    private String id;
    private String displayName;
    private List<DataElementForGroupsDTO> dataElements;
}
