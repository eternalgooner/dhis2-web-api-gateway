package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.repository.DataElementGroupRepository;
import com.eternalgooner.dhis2webapigateway.repository.DataElementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.eternalgooner.dhis2webapigateway.mapper.EntityToDtoMapperTest.*;
import static com.eternalgooner.dhis2webapigateway.util.GenDataUtil.genDataElement;
import static com.eternalgooner.dhis2webapigateway.util.GenDataUtil.genDataElementGroup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Dhis2DataServiceTest {

    @InjectMocks
    private Dhis2DataService dhis2DataService;

    @Mock
    private DbInitService dbInitService;

    @Mock
    private DataElementRepository dataElementRepository;

    @Mock
    private DataElementGroupRepository dataElementGroupRepository;

    @Test
    public void getDataElementsTest() {
        //given
        DataElement dataElement = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElementGroup dataElementGroup = genDataElementGroup(ID_1, GROUP_NAME_1);
        dataElement.setDataElementGroups(Set.of(dataElementGroup));
        List<DataElement> dataElements = List.of(dataElement);
        when(dataElementRepository.findAll()).thenReturn(dataElements);

        //when
        final DataElementsResponseDTO dataElementsResponseDTO = dhis2DataService.getDataElements().block();

        //then
        assertNotNull(dataElementsResponseDTO);
        assertEquals(1, dataElementsResponseDTO.getDataElements().size());
    }

    @Test
    public void getDataElementGroupsTest() {
        //given
        DataElement dataElement = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElementGroup dataElementGroup = genDataElementGroup(ID_1, GROUP_NAME_1);
        dataElementGroup.setDataElements(Set.of(dataElement));
        List<DataElementGroup> dataElementGroups = List.of(dataElementGroup);
        when(dataElementGroupRepository.findAll()).thenReturn(dataElementGroups);

        //when
        final DataElementGroupsResponseDTO dataElementGroupsResponseDTO = dhis2DataService.getDataElementGroups().block();

        //then
        assertNotNull(dataElementGroupsResponseDTO);
        assertEquals(1, dataElementGroupsResponseDTO.getMembers().size());
    }

    @Test
    public void getDataElementsNotInDbTest() {
        //given
        DataElement dataElement = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElementGroup dataElementGroup = genDataElementGroup(ID_1, GROUP_NAME_1);
        dataElement.setDataElementGroups(Set.of(dataElementGroup));
        List<DataElement> dataElements = List.of(dataElement);
        when(dataElementRepository.findAll()).thenReturn(Collections.emptyList(), dataElements);
        doNothing().when(dbInitService).getDhis2DataAndLoadIntoDb();

        //when
        final DataElementsResponseDTO dataElementsResponseDTO = dhis2DataService.getDataElements().block();

        //then
        verify(dbInitService).getDhis2DataAndLoadIntoDb();
        assertNotNull(dataElementsResponseDTO);
        assertEquals(1, dataElementsResponseDTO.getDataElements().size());
    }

    @Test
    public void getDataElementGroupsNotInDbTest() {
        //given
        DataElement dataElement = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElementGroup dataElementGroup = genDataElementGroup(ID_1, GROUP_NAME_1);
        dataElementGroup.setDataElements(Set.of(dataElement));
        List<DataElementGroup> dataElementGroups = List.of(dataElementGroup);
        when(dataElementGroupRepository.findAll()).thenReturn(Collections.emptyList(), dataElementGroups);
        doNothing().when(dbInitService).getDhis2DataAndLoadIntoDb();

        //when
        final DataElementGroupsResponseDTO dataElementGroupsResponseDTO = dhis2DataService.getDataElementGroups().block();

        //then
        verify(dbInitService).getDhis2DataAndLoadIntoDb();
        assertNotNull(dataElementGroupsResponseDTO);
        assertEquals(1, dataElementGroupsResponseDTO.getMembers().size());
    }
}
