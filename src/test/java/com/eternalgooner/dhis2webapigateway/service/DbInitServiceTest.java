package com.eternalgooner.dhis2webapigateway.service;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.repository.DataElementGroupRepository;
import com.eternalgooner.dhis2webapigateway.repository.DataElementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.eternalgooner.dhis2webapigateway.mapper.EntityToDtoMapperTest.*;
import static com.eternalgooner.dhis2webapigateway.util.GenDataUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbInitServiceTest {

    @InjectMocks
    private DbInitService dbInitService;

    @Mock
    private Dhis2WebApiService dhis2WebApiService;

    @Mock
    private DataElementRepository dataElementRepository;

    @Mock
    private DataElementGroupRepository dataElementGroupRepository;

    @Test
    public void getDhis2DataAndLoadIntoDbTest() {
        //given
        DataElementsDTO dataElementsDTO = DataElementsDTO.builder()
                .dataElements(List.of(genDataElementDTO(ID_1, ELEMENT_NAME_1))).build();
        DataElementGroupsDTO dataElementGroupsDTO = DataElementGroupsDTO.builder()
                .dataElementGroups(List.of(genDataElementGroupDTO(ID_1, GROUP_NAME_1))).build();
        when(dhis2WebApiService.getDataElements()).thenReturn(dataElementsDTO);
        when(dhis2WebApiService.getDataElementGroups()).thenReturn(dataElementGroupsDTO);

        //when
        dbInitService.getDhis2DataAndLoadIntoDb();

        //then
        verify(dataElementRepository).saveAll(anyList());
        verify(dataElementGroupRepository).saveAll(anyList());
    }

}
