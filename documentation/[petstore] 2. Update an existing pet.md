### Test Design

### Requirements

Functional Requirements: user should be able to update an existing pet

### Test Scenario Positive

**1** User updates parameter(s) of the existing pet
```gherkin
Given Create new pet object with valid input parameters: id, category, name, photoUrls, tags, status
    And Update the parameter(s)
When Put request is sent
Then Status code 200
    And Response pet object parameters correspond to the preassigned pet object parameters
```
### Test Scenario Negative

**2** User tries to update a pet using invalid parameter(s)
```gherkin
Given Create pet object with invalid parameter(s)
When Put request is sent
Then Status code 400
```

**3** User tries to update a pet using empty parameter(s)
```gherkin
Given Create pet object with empty parameter(s)
When Put request is sent
Then Status code 404
```