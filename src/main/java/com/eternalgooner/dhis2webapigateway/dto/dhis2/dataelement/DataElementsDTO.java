package com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataElementsDTO {
    private List<DataElementDTO> dataElements;
}
