package org.hisp.dhis.gateway.dhis2webapigateway.repository;

import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataElementRepository extends CrudRepository<DataElement, String> {
}
