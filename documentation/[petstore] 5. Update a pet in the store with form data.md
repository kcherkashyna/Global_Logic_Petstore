### Test Design

### Requirements

Functional Requirements: user should be able to update a pet in the store with form data

### Test Scenario Positive

**1** User updates a pet parameter(s): id, name, status
```gherkin
Given Created and updated pet object
When Post request is sent
Then Status code 200
```
### Test Scenario Negative

**2** User tries to update a non-existent pet (with non-existent id, name and status)
```gherkin
Given Created pet object and updated with non-existent id, name and status
When Post request is sent
Then Status code 405. Invalid input
```
