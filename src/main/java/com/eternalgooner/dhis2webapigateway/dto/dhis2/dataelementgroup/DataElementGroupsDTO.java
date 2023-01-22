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
public class DataElementGroupsDTO {
    private List<DataElementGroupDTO> dataElementGroups;
}
