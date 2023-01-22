package com.eternalgooner.dhis2webapigateway.controller;

import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelement.DataElementsResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupResponseDTO;
import com.eternalgooner.dhis2webapigateway.dto.api.dataelementgroup.DataElementGroupsResponseDTO;
import com.eternalgooner.dhis2webapigateway.service.Dhis2DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@ExtendWith({SpringExtension.class})
@WebFluxTest({DataElementsController.class})
@AutoConfigureRestDocs
public class DataElementsControllerIT {

    @InjectMocks
    private DataElementsController dataElementsController;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private Dhis2DataService dhis2DataService;

    @Test
    public void getDataElementsIT() {
        DataElementResponseDTO dataElementResponseDTO = DataElementResponseDTO.builder()
                .id("id123")
                .name("element name 1")
                .groups(Set.of("group 1", "group 2")).build();
        DataElementsResponseDTO dataElementsResponseDTO = DataElementsResponseDTO.builder()
                .dataElements(Set.of(dataElementResponseDTO)).build();
        when(dhis2DataService.getDataElements()).thenReturn(dataElementsResponseDTO);

        webTestClient.get()
                .uri("/api/dataElements")
                .headers(headers -> headers.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].id").isEqualTo("id123")
                .jsonPath("$[0].name").isEqualTo("element name 1")
                .jsonPath("$[0].groups.length()").isEqualTo(2)
                .consumeWith(document("data-elements"));
    }

    @Test
    public void getDataElementGroupsIT() {
        DataElementGroupResponseDTO dataElementGroupResponseDTO = DataElementGroupResponseDTO.builder()
                .id("id9")
                .name("group name 1")
                .members(Set.of("element 1", "element 2")).build();
        DataElementGroupsResponseDTO dataElementGroupsResponseDTO = DataElementGroupsResponseDTO.builder()
                .members(Set.of(dataElementGroupResponseDTO)).build();
        when(dhis2DataService.getDataElementGroups()).thenReturn(dataElementGroupsResponseDTO);

        webTestClient.get()
                .uri("/api/dataElementGroups")
                .headers(headers -> headers.setBasicAuth("user", "password"))
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].id").isEqualTo("id9")
                .jsonPath("$[0].name").isEqualTo("group name 1")
                .jsonPath("$[0].members.length()").isEqualTo(2)
                .consumeWith(document("data-element-groups"));
    }
}
