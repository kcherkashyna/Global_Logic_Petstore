### Test Design

### Requirements

Functional Requirements: user should be able to update a pet using post method

### Test Scenario Positive

**1** User updates a pet parameter(s)
```gherkin
Given Create and update pet object
When Post request is sent
Then Status code 200
    And Response properties correspond to the expected
    And Response pet object parameters id, name, status correspond to the expected
```
### Test Scenario Negative

**2** User tries to update a pet using invalid parameter(s)
```gherkin
Given Create and update pet object with invalid parameter(s)
When Post request is sent
Then Status code 400
```

**3** User tries to update a pet using empty parameter(s)
```gherkin
Given Create and update pet object with empty parameter(s)
When Post request is sent
Then Status code 404
```
