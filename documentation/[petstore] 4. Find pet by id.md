### Test Design

### Requirements

Functional Requirements: user should be able to find pet by id

### Test Scenario Positive

**1** User finds pet by id
```gherkin
Given Create pet object
When Get request is sent
Then Status code 200
    And Response properties correspond to the expected
    And Response pet object parameters id, name, status correspond to the expected
```
### Test Scenario Negative

**2** User tries to find pet with invalid parameter(s)
```gherkin
Given Create pet object with invalid parameter(s)
When Get request is sent
Then Status code 400
```
**3** User tries to find pet with empty parameter(s)
```gherkin
Given Create pet object with empty parameter(s)
When Get request is sent
Then Status code 404
```
**4** User tries to find pet using invalid method
```gherkin
Given Create pet object
When Patch request is sent
Then Status code 405
```
