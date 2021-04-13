### Test Design

### Requirements

Functional Requirements: user should be able to update an existing pet

### Test Scenario Positive

**1** User updates a name of the existing pet
```gherkin
Given Created pet object
When Put request is sent
Then Status code 200
```
### Test Scenario Negative

**2** User tries to update a non-existent pet (with non-existent id)
```gherkin
Given Created pet object with non-existent id
When Put request is sent
Then Status code 400. Invalid ID supplied
```

**3** User tries to update a non-existent pet (with non-existent parameter(s))
```gherkin
Given Created pet object with non-existent parameter(s)
When Put request is sent
Then Status code 404. petstore.Pet not found
```

**4** User tries to update a pet with invalid parameter(s)
```gherkin
Given Created pet object with invalid parameter(s)
When Put request is sent
Then Status code 405. Validation exception
```