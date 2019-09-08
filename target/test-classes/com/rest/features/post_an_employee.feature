Feature: Employee Rest Api request

  @ApiPost
  Scenario: Post an employee method test
    Given Content type and Accept type is Json
    When I post a new Employee with "random" id
    Then status code is 201
    And Request json should contain Employee info
    When I send a get request with "random" id
    Then status code is 200
    And employee Json response data should match the posted json data



