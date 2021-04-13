### Test Design

### Requirements

Functional Requirements: user should be able to add a new pet

### Test Scenario Positive

**1** User adds a new pet to the store with valid input parameters
```gherkin
Given Create new pet object with parameters: id, category, name, photoUrls, tags, status
When Post request is sent
Then Status code 200
```
### Test Scenario Negative
**2** User tries to add a new pet to the store with invalid input parameter(s)
```gherkin
Given Create new pet object with invalid input parameters: id, category, name, photoUrls, tags, status
When Post request is sent
Then Status code 405
```