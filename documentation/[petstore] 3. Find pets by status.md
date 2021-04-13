### Test Design

### Requirements

Functional Requirements: user should be able to find pets by status

### Test Scenario Positive

**1** User finds pets by status
```gherkin
Given Create pet object with one of these statuses: available, pending, sold
    And Status parameter is specified in request
When Get request is sent
Then Status code 200
```
### Test Scenario Negative

**2** User tries to find pets with non-existent status
```gherkin
Given Create pet object with non-existent status
When Get request is sent
Then Status code 400
```

**3** User tries to find pets with empty status
```gherkin
Given Create pet object with empty status
When Get request is sent
Then Status code 404
```

**4** User tries to find pets with valid status using invalid method
```gherkin
Given Create pet object with valid status
When Patch request is sent
Then Status code 405
```
