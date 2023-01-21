package org.hisp.dhis.gateway.dhis2webapigateway.mapper;

import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelement.DataElementsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.dto.dhis2.dataelementgroup.DataElementGroupsDTO;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElement;
import org.hisp.dhis.gateway.dhis2webapigateway.entity.DataElementGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hisp.dhis.gateway.dhis2webapigateway.util.GenDataUtil.genDataElementDTO;
import static org.hisp.dhis.gateway.dhis2webapigateway.util.GenDataUtil.genDataElementGroupDTO;
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
        DataElementDTO dataElementDTO1 = genDataElementDTO();
        DataElementDTO dataElementDTO2 = genDataElementDTO();
        DataElementsDTO dataElementsDTO = DataElementsDTO.builder()
                .dataElements(List.of(dataElementDTO1, dataElementDTO2)).build();

        //when
        List<DataElement> dataElements = dtoToEntityMapper.dataElementsDtoToEntities.apply(dataElementsDTO);

        //then
        DataElement dataElement = dataElements.get(0);
        assertEquals(2, dataElements.size());
        assertEquals(1, dataElement.getDataElementGroups().size());
        assertEquals("id1", dataElement.getId());
        assertEquals("name1", dataElement.getDisplayName());
    }

    @Test
    public void dataElementDtoTOEntityTest() {
        //given
        DataElementDTO dataElementDTO = genDataElementDTO();

        //when
        DataElement dataElement = dtoToEntityMapper.dataElementDtoToEntity.apply(dataElementDTO);

        //then
        assertEquals(1, dataElement.getDataElementGroups().size());
        assertEquals("id1", dataElement.getId());
        assertEquals("name1", dataElement.getDisplayName());
    }

    @Test
    public void dataElementsGroupDtoTOEntityTest() {
        //given
        DataElementGroupDTO dataElementGroupDTO1 = genDataElementGroupDTO();
        DataElementGroupDTO dataElementGroupDTO2= genDataElementGroupDTO();
        DataElementGroupsDTO dataElementGroupsDTO = DataElementGroupsDTO.builder()
                .dataElementGroups(List.of(dataElementGroupDTO1, dataElementGroupDTO2)).build();

        //when
        List<DataElementGroup> dataElementGroups = dtoToEntityMapper.dataElementGroupsDtoToEntities.apply(dataElementGroupsDTO);

        //then
        DataElementGroup dataElementGroup = dataElementGroups.get(0);
        assertEquals(2, dataElementGroups.size());
        assertEquals(1, dataElementGroup.getDataElements().size());
        assertEquals("id1", dataElementGroup.getId());
        assertEquals("name1", dataElementGroup.getDisplayName());
    }

    @Test
    public void dataElementGroupDtoTOEntityTest() {
        //given
        DataElementGroupDTO dataElementGroupDTO = genDataElementGroupDTO();

        //when
        DataElementGroup dataElementGroup = dtoToEntityMapper.dataElementGroupDtoToEntity.apply(dataElementGroupDTO);

        //then
        assertEquals(1, dataElementGroup.getDataElements().size());
        assertEquals("id1", dataElementGroup.getId());
        assertEquals("name1", dataElementGroup.getDisplayName());
    }
}
