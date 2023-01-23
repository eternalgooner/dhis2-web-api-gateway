package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementGroupForElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementForGroupsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.repository.DataElementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DbInitServiceIT {

    @Autowired
    private DbInitService dbInitService;

    @Autowired
    private DataElementRepository dataElementRepository;

    @MockBean
    private Dhis2WebApiService dhis2WebApiService;

    @Test
    public void getDhis2DataAndLoadIntoDbIT() {
        // given
        DataElementsDTO dataElementsDTO = genDataElementsDTO();
        DataElementGroupsDTO dataElementGroupsDTO = genDataElementGroupsDTO();

        when(dhis2WebApiService.getDataElements()).thenReturn(dataElementsDTO);
        when(dhis2WebApiService.getDataElementGroups()).thenReturn(dataElementGroupsDTO);

        dbInitService.getDhis2DataAndLoadIntoDb();

        // when
        final List<DataElement> dataElements = StreamSupport.stream(dataElementRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        // then
        DataElement dataElement = dataElements.get(0);
        assertNotNull(dataElements);
        assertNotNull(dataElement.getId());
        assertNotNull(dataElement.getDisplayName());
        assertNotNull(dataElement.getDataElementGroups());
        assertNotNull(dataElement.getDataElementGroups());
    }

    private DataElementsDTO genDataElementsDTO() {
        DataElementGroupForElementsDTO dataElementGroupForElementsDTO1 = DataElementGroupForElementsDTO.builder()
                .id("groupId1").build();
        DataElementGroupForElementsDTO dataElementGroupForElementsDTO2 = DataElementGroupForElementsDTO.builder()
                .id("groupId2").build();
        DataElementDTO dataElementDTO1 = DataElementDTO.builder()
                .id("elId1")
                .displayName("elName1")
                .dataElementGroups(List.of(dataElementGroupForElementsDTO1, dataElementGroupForElementsDTO2)).build();
        DataElementDTO dataElementDTO2 = DataElementDTO.builder()
                .id("elId2")
                .displayName("elName2")
                .dataElementGroups(List.of(dataElementGroupForElementsDTO1, dataElementGroupForElementsDTO2)).build();
        return DataElementsDTO.builder()
                .dataElements(List.of(dataElementDTO1, dataElementDTO2)).build();
    }

    private DataElementGroupsDTO genDataElementGroupsDTO() {
        DataElementForGroupsDTO dataElementForGroupsDTO1 = DataElementForGroupsDTO.builder()
                .id("elId1").build();
        DataElementForGroupsDTO dataElementForGroupsDTO2 = DataElementForGroupsDTO.builder()
                .id("elId2").build();
        DataElementGroupDTO dataElementGroupDTO1 = DataElementGroupDTO.builder()
                .id("groupId1")
                .displayName("groupName1")
                .dataElements(List.of(dataElementForGroupsDTO1, dataElementForGroupsDTO2)).build();
        DataElementGroupDTO dataElementGroupDTO2 = DataElementGroupDTO.builder()
                .id("groupId2")
                .displayName("groupName2")
                .dataElements(List.of(dataElementForGroupsDTO1, dataElementForGroupsDTO2)).build();
        return DataElementGroupsDTO.builder()
                .dataElementGroups(List.of(dataElementGroupDTO1, dataElementGroupDTO2)).build();
    }

}
