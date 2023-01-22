package com.eternalgooner.dhis2webapigateway.mapper;

import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupDTO;
import com.eternalgooner.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.eternalgooner.dhis2webapigateway.mapper.EntityToDtoMapperTest.*;
import static com.eternalgooner.dhis2webapigateway.util.GenDataUtil.genDataElementDTO;
import static com.eternalgooner.dhis2webapigateway.util.GenDataUtil.genDataElementGroupDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DtoToEntityMapperTest {

    private DtoToEntityMapper dtoToEntityMapper;

    @BeforeEach
    public void init() {
        dtoToEntityMapper = new DtoToEntityMapper();
    }

    @Test
    public void dataElementsDtoTOEntityTest() {
        //given
        DataElementDTO dataElementDTO1 = genDataElementDTO(ID_1, ELEMENT_NAME_1);
        DataElementDTO dataElementDTO2 = genDataElementDTO(ID_2, ELEMENT_NAME_2);
        DataElementsDTO dataElementsDTO = DataElementsDTO.builder()
                .dataElements(List.of(dataElementDTO1, dataElementDTO2)).build();

        //when
        List<DataElement> dataElements = dtoToEntityMapper.dataElementsDtoToEntities.apply(dataElementsDTO);

        //then
        assertEquals(2, dataElements.size());
    }

    @Test
    public void dataElementDtoTOEntityTest() {
        //given
        DataElementDTO dataElementDTO = genDataElementDTO(ID_1, ELEMENT_NAME_1);

        //when
        DataElement dataElement = dtoToEntityMapper.dataElementDtoToEntity.apply(dataElementDTO);

        //then
        assertEquals(1, dataElement.getDataElementGroups().size());
        assertEquals(ID_1, dataElement.getId());
        assertEquals(ELEMENT_NAME_1, dataElement.getDisplayName());
    }

    @Test
    public void dataElementsGroupDtoTOEntityTest() {
        //given
        DataElementGroupDTO dataElementGroupDTO1 = genDataElementGroupDTO(ID_1, GROUP_NAME_1);
        DataElementGroupDTO dataElementGroupDTO2= genDataElementGroupDTO(ID_2, GROUP_NAME_2);
        DataElementGroupsDTO dataElementGroupsDTO = DataElementGroupsDTO.builder()
                .dataElementGroups(List.of(dataElementGroupDTO1, dataElementGroupDTO2)).build();

        //when
        List<DataElementGroup> dataElementGroups = dtoToEntityMapper.dataElementGroupsDtoToEntities.apply(dataElementGroupsDTO);

        //then
        assertEquals(2, dataElementGroups.size());
    }

    @Test
    public void dataElementGroupDtoTOEntityTest() {
        //given
        DataElementGroupDTO dataElementGroupDTO = genDataElementGroupDTO(ID_1, GROUP_NAME_1);

        //when
        DataElementGroup dataElementGroup = dtoToEntityMapper.dataElementGroupDtoToEntity.apply(dataElementGroupDTO);

        //then
        assertEquals(1, dataElementGroup.getDataElements().size());
        assertEquals(ID_1, dataElementGroup.getId());
        assertEquals(GROUP_NAME_1, dataElementGroup.getDisplayName());
    }
}
