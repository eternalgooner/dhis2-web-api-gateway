package org.hisp.dhis.gateway.dhis2webapigateway.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DataElementGroup {
    @Id
    private String id;
    private String displayName;

    @ManyToMany(mappedBy = "dataElementGroups")
    private Set<DataElement> dataElements;
}
