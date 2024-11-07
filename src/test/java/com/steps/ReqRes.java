package com.steps;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqRes {

	RequestSpecification requestSpec;
	Response response;
	HashMap responseData;
	JsonPath jsonData;
	
	@Given("prepare request based URL {string}")
	public void prepare_request_based_url(String baseURL) {
	    RestAssured.baseURI = baseURL;
	}
	
	@Given("prepare request end point {string}")
	public void prepare_request_end_point(String endpoint) {
	    RestAssured.basePath = endpoint;
	    requestSpec = RestAssured.given();
	}
	
	@Given("prepare request body with valid data")
	public void prepare_request_body_with_valid_data() throws IOException, ParseException {
	   FileReader reader = new FileReader("src/test/resources/request.json");
	   JSONParser parser = new JSONParser();
	   JSONObject jsonObject = (JSONObject) parser.parse(reader);
	   requestSpec.body(jsonObject);
	}
	
	@When("select GET http method and send api request")
	public void select_get_http_method_and_send_api_request() {
		requestSpec.queryParam("page", "2");
	    response = requestSpec.request(Method.GET);
	}
	
	@When("select POST http method and send api request")
	public void select_post_http_method_and_send_api_request() {
	    response = requestSpec.contentType("application/json").when().post();
	}
	
	@Then("validate response status code {int}")
	public void validate_response_status_code(Integer expStatusCode) {
	   Integer actStatusCode = response.statusCode();
	   Assert.assertEquals(expStatusCode, actStatusCode);	   
	}
	
	@Then("validate response body should have page number {int}")
	public void validate_response_body_should_have_page_number(Integer expPageNumber) {
		System.out.println("Response Body: "+response.path("data"));
		Integer actPageNumber = response.path("page");
		System.out.println("Response Page: "+actPageNumber);
		Assert.assertEquals(expPageNumber, actPageNumber);
	}
	
	@Then("validate response body should have user details")
	public void validate_response_body_should_have_user_details() {
	    String actName = response.path("name");
	    Assert.assertEquals(actName, "Queue Codes");
	}
}
