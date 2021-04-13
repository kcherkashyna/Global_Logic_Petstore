### Test Design

### Requirements

Functional Requirements: user should be able to find pets by status

### Test Scenario Positive

**1** User find pets by status
```gherkin
Given Created pet object with one of these statuses: available, pending, sold
When Get request is sent
Then Status code 200
```
### Test Scenario Negative

**2** User tries to find pets with non-existent status
```gherkin
Given Created pet object with non-existent status
When Get request is sent
Then Status code 400. Invalid status value
```
