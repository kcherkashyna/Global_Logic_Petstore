### Test Design

### Requirements

Functional Requirements: user should be able to delete a pet

### Test Scenario Positive

**1** User deletes pet by id
```gherkin
Given Created pet object
    And api_key and petId are specified
When Delete request is sent
Then Status code 200
```
### Test Scenario Negative

**2** User tries to delete pet using non-existent petId
```gherkin
Given Created pet object
    And Invalid petId is specified
When Delete request is sent
Then Status code 400. Invalid ID supplied
```
**3** User tries to delete pet using invalid parameter(s)
```gherkin
Given Created pet object with using invalid parameter(s)
When Delete request is sent
Then Status code 404. Pet not found
```
