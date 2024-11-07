@APITesting
Feature: To validate api response from reqres api

@GetAPI
Scenario: To validate users list with success status code
	Given prepare request based URL "https://reqres.in/"
	And prepare request end point "/api/users"
	When select GET http method and send api request 
	Then validate response status code 200
	And validate response body should have page number 2
	
@PostAPI	
Scenario: To validate user should create new account with valid details
	Given prepare request based URL "https://reqres.in/"
	And prepare request end point "/api/users"
	And prepare request body with valid data
	When select POST http method and send api request 
	Then validate response status code 201
	And validate response body should have user details
