package com.eternalgooner.dhis2webapigateway.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
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
//    @ToString.Exclude
    private Set<DataElementGroup> dataElementGroups;

    public void setDataElementGroups(Set<DataElementGroup> dataElementGroups) {
        this.dataElementGroups = dataElementGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataElement)) return false;
        DataElement that = (DataElement) o;
        return getId().equals(that.getId()) && getDisplayName().equals(that.getDisplayName()) && Objects.equals(getDataElementGroups(), that.getDataElementGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDisplayName(), getDataElementGroups());
    }
}
