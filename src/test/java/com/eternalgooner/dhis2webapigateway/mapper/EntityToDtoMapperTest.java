package com.eternalgooner.dhis2webapigateway.mapper;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.entity.DataElement;
import com.eternalgooner.dhis2webapigateway.entity.DataElementGroup;
import com.eternalgooner.dhis2webapigateway.util.GenDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.eternalgooner.dhis2webapigateway.util.GenDataUtil.genDataElement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityToDtoMapperTest {

    private EntityToDtoMapper entityToDtoMapper;
    public static final String ID_1  = "id1";
    public static final String ID_2  = "id2";
    public static final String ELEMENT_NAME_1  = "element name 1";
    public static final String ELEMENT_NAME_2  = "element name 2";
    public static final String GROUP_NAME_1  = "group name 1";
    public static final String GROUP_NAME_2  = "group name 2";

    @BeforeEach
    public void init() {
        entityToDtoMapper = new EntityToDtoMapper();
    }

    @Test
    public void dataElementsEntitiesToDtoTest() {
        //given
        DataElement dataElement1 = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElement dataElement2 = genDataElement(ID_2, ELEMENT_NAME_2);

        DataElementGroup dataElementGroup1 = GenDataUtil.genDataElementGroup(ID_1, GROUP_NAME_1);
        DataElementGroup dataElementGroup2 = GenDataUtil.genDataElementGroup(ID_2, GROUP_NAME_2);

        dataElement1.setDataElementGroups(Set.of(dataElementGroup1, dataElementGroup2));
        dataElement2.setDataElementGroups(Set.of(dataElementGroup1, dataElementGroup2));

        //when
        DataElementsResponseDTO dataElementsResponseDTO = entityToDtoMapper.dataElEntitiesToDto.apply(List.of(dataElement1, dataElement2));

        //then
        assertEquals(2, dataElementsResponseDTO.getDataElements().size());
    }

    @Test
    public void dataElementEntityToDtoTest() {
        //given
        DataElement dataElement = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElementGroup dataElementGroup1 = GenDataUtil.genDataElementGroup(ID_1, GROUP_NAME_1);
        DataElementGroup dataElementGroup2 = GenDataUtil.genDataElementGroup(ID_2, GROUP_NAME_2);
        dataElement.setDataElementGroups(Set.of(dataElementGroup1, dataElementGroup2));

        //when
        DataElementResponseDTO dataElementResponseDTO = entityToDtoMapper.dataElEntityToDataElRespDto.apply(dataElement);

        //then
        assertEquals(2, dataElementResponseDTO.getGroups().size());
        assertTrue(dataElementResponseDTO.getGroups().containsAll(Set.of(GROUP_NAME_1, GROUP_NAME_2)));
        assertEquals(ID_1, dataElementResponseDTO.getId());
        assertEquals(ELEMENT_NAME_1, dataElementResponseDTO.getName());
    }

    @Test
    public void dataElementGroupsEntitiesToDtoTest() {
        //given
        DataElement dataElement1 = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElement dataElement2 = genDataElement(ID_2, ELEMENT_NAME_2);

        DataElementGroup dataElementGroup1 = GenDataUtil.genDataElementGroup(ID_1, GROUP_NAME_1);
        DataElementGroup dataElementGroup2 = GenDataUtil.genDataElementGroup(ID_2, GROUP_NAME_2);

        dataElementGroup1.setDataElements(Set.of(dataElement1, dataElement2));
        dataElementGroup2.setDataElements(Set.of(dataElement1, dataElement2));

        //when
        DataElementGroupsResponseDTO dataElementGroupsResponseDTO = entityToDtoMapper.dataElGroupEntitiesToDto.apply(List.of(dataElementGroup1, dataElementGroup2));

        //then
        assertEquals(2, dataElementGroupsResponseDTO.getMembers().size());
    }

    @Test
    public void dataElementGroupEntityToDtoTest() {
        //given
        DataElement dataElement1 = genDataElement(ID_1, ELEMENT_NAME_1);
        DataElement dataElement2 = genDataElement(ID_2, ELEMENT_NAME_2);

        DataElementGroup dataElementGroup = GenDataUtil.genDataElementGroup(ID_1, GROUP_NAME_1);
        dataElementGroup.setDataElements(Set.of(dataElement1, dataElement2));

        //when
        DataElementGroupResponseDTO dataElementGroupResponseDTO = entityToDtoMapper.dataElGroupEntityToDataElGroupRespDto.apply(dataElementGroup);

        //then
        assertEquals(2, dataElementGroupResponseDTO.getMembers().size());
        assertEquals(ID_1, dataElementGroupResponseDTO.getId());
        assertEquals(GROUP_NAME_1, dataElementGroupResponseDTO.getName());
        assertTrue(dataElementGroupResponseDTO.getMembers().containsAll(Set.of(ELEMENT_NAME_1, ELEMENT_NAME_2)));
    }

}
