package org.hisp.dhis.gateway.dhis2webapigateway.domain;

import lombok.Data;

import java.util.List;

@Data
public class DataElement {
    private String id;
    private String displayName;
    private List<DataElementGroup> dataElementGroups;
}
