$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/com/rest/features/post_an_employee.feature");
formatter.feature({
  "name": "Employee Rest Api request",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Post an employee method test",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@ApiPost"
    }
  ]
});
formatter.step({
  "name": "Content type and Accept type is Json",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I post a new Employee with \"random\" id",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "status code is 201",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "Request json should contain Employee info",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "I send a get request with \"random\" id",
  "keyword": "When "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "status code is 200",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.step({
  "name": "employee Json response data should match the posted json data",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});