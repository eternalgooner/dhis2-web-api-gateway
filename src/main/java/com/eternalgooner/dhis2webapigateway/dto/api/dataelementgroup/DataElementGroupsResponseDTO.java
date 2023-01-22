package com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class DataElementGroupsResponseDTO {

    @JsonValue
    private Set<DataElementGroupResponseDTO> members;
}
