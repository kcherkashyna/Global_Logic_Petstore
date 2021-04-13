### Test Design

### Requirements

Functional Requirements: user should be able to delete a pet

### Test Scenario Positive

**1** User deletes pet by id
```gherkin
Given Create pet object
When Delete request is sent
Then Status code 200
```
### Test Scenario Negative

**2** User tries to delete pet with invalid petId
```gherkin
Given Create pet object
    And Invalid petId is specified
When Delete request is sent
Then Status code 400
```
**3** User tries to delete pet with empty petId
```gherkin
Given Create pet object
    And Empty petId is specified
When Delete request is sent
Then Status code 404
```