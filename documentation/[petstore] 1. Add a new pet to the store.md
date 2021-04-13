### Test Design

### Requirements

Functional Requirements: user should be able to add a new pet

### Test Scenario Positive

**1** User adds a new pet to the store with valid input parameters
```gherkin
Given Create new pet object with valid input parameters: id, category, name, photoUrls, tags, status
When Post request is sent
Then Status code 200
    And Response properties correspond to the expected
    And Response pet object parameters id, name, status correspond to the expected
```
### Test Scenario Negative
**2** User tries to add a new pet to the store with invalid parameter(s)
```gherkin
Given Create new pet object with invalid parameter(s)
When Post request is sent
Then Status code 400
```
**3** User tries to add a new pet to the store with empty parameter(s)
```gherkin
Given Create new pet object with empty parameter(s)
When Post request is sent
Then Status code 404
```
**4** User tries to add a new pet to the store with invalid method
```gherkin
Given Create new pet object with valid input parameters
When Patch request is sent
Then Status code 405
```