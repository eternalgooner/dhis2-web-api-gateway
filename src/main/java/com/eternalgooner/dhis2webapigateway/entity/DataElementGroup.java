package com.eternalgooner.dhis2webapigateway.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DataElementGroup {
    @Id
    private String id;
    private String displayName;

    @ManyToMany(mappedBy = "dataElementGroups")
    private Set<DataElement> dataElements;

    public void setDataElements(Set<DataElement> dataElements) {
        this.dataElements = dataElements;
    }
}
