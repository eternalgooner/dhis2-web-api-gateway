package com.eternalgooner.dhis2webapigateway.repository;

import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataElementGroupRepository extends CrudRepository<DataElementGroup, String> {
}
