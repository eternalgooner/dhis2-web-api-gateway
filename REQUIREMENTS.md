# Requirements

## Functional
- [ ] build integration service (Gateway) between DHIS2 Web API & an external service
    - [ ] fetch meta data from DHIS2
        - [x] data elements from /api/dataElements.json?paging=false&fields=id,displayName,dataElementGroups[id]
        - [x] data element groups from /api/dataElementGroups.json?paging=false&fields=id,displayName,dataElements[id]
        - [ ] option to add more
        - [x] define own data format for internal storage
    - [x] make meta data above available for external consumption
        - [x] expose through REST endpoints
    - [x] have DHIS2 instance configurable in properties e.g.
        - [x] dhis2.username = admin
        - [x] dhis2.password = district
        - [x] dhis2.url = https://play.dhis2.org/2.39.0.1
    - [x] add security through Spring Security
        - [x] in-memory username/password

## Non-functional
- [ ] test service with mocked payloads
    - [x] at least 1 integration test
    - [x] mock DHIS2 instance

## Technical
- [x] use maven as build tool
- [x] use spring boot 2+
- [x] use Jackson for serdes
- [x] use spring security to secure endpoints
- [ ] metadata from DHIS2 should be cached or persisted in the service
  - [ ] include javadoc to explain caching & persisting meta data
  - [ ] describe pros and cons of design
    - [x] expose 2 endpoints from the service
      - [x] /api/dataElements
        ```json
        {
          "id": "id",
          "name": "name",
          "groups": ["group1", "group2"]
        }
        ```
      - [x] /api/dataElementGroups
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
