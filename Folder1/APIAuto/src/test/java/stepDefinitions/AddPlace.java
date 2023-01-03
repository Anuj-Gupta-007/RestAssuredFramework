package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import PoJo.AddLocation;
import PoJo.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;
import org.hamcrest.Matchers.*;

public class AddPlace extends Utils{

	RequestSpecification request;
	ResponseSpecification specResponse;
	public Response response;
	TestDataBuild tdb = new TestDataBuild();
	
	@Given("User has add place payload with {string} {string} {string}")
	public void user_has_add_place_payload_with(String name, String language, String address) throws IOException {
		
		request = given().spec(requestSpecification())
				.body(tdb.addPlacePayload(name, language, address));	
	}

	@When("User calls {string} with Post request")
	public void user_calls_with_Post_request(String resource1) {
		
		
		APIResources resourceAPI = APIResources.valueOf(resource1);
		System.out.println(resourceAPI.getResource());
		
		specResponse = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build(); 
		
		response = request.when()
				.post(resourceAPI.getResource())
				.then().spec(specResponse).extract().response();
		
	}

	@Then("User verify that the API call is success with status code {int}")
	public void user_verify_that_the_api_call_is_success_with_status_code(Integer int1) {
		Assert.assertEquals(response.getStatusCode(),int1);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
	
		String actualValue = getJSONPath(response, key);
		Assert.assertEquals(actualValue,expectedValue);
		System.out.println(actualValue);
	}
}
