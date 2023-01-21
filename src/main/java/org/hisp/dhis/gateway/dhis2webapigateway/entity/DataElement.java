package org.hisp.dhis.gateway.dhis2webapigateway.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DataElement {
    @Id
    private String id;
    private String displayName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "data_element_data_element_group",
            joinColumns = @JoinColumn(name = "data_element_id"),
            inverseJoinColumns = @JoinColumn(name = "data_element_group_id")
    )
    private Set<DataElementGroup> dataElementGroups;
}
