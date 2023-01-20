# Requirements

## Functional
- [ ] build integration service (Gateway) between DHIS2 Web API & an external service
    - [ ] fetch meta data from DHIS2
        - [ ] data elements from /api/dataElements.json?paging=false&fields=id,displayName,dataElementGroups[id]
        - [ ] data element groups from /api/dataElementGroups.json?paging=false&fields=id,displayName,dataElements[id]
        - [ ] option to add more
        - [ ] define own data format for internal storage
    - [ ] make meta data above available for external consumption
        - [ ] expose through REST endpoints
    - [ ] have DHIS2 instance configurable in properties e.g.
        - [ ] dhis2.username = admin
        - [ ] dhis2.password = district
        - [ ] dhis2.url = https://play.dhis2.org/2.39.0.1
    - [ ] add security through Spring Security
        - [ ] in-memory username/password

## Non-functional
- [ ] test service with mocked payloads
    - [ ] at least 1 integration test
    - [ ] mock DHIS2 instance

## Technical
- [ ] use maven as build tool
- [ ] use spring boot 2+
- [ ] use Jackson for serdes
- [ ] use spring security to secure endpoints
- [ ] metadata from DHIS2 should be cached or persisted in the service
  - [ ] include javadoc to explain caching & persisting meta data
  - [ ] describe pros and cons of design
    - [ ] expose 2 endpoints from the service
      - [ ] /api/dataElements
        ```json
        {
          "id": "id",
          "name": "name",
          "groups": ["group1", "group2"]
        }
        ```
      - [ ] /api/dataElementGroups
        ```json
        {
          "id": "id",
          "name": "name",
          "members": ["dataElement1", "dataElement2"]
        }
        ```
- [ ] endpoint formats should be resolved
    - [ ] looking at the file extension first
    - [ ] then the Accept header
    - [ ] default (\*/\*) should be JSON

## Optional tech requirements
- [ ] document endpoints using spring rest docs or OpenAPI/Swagger
