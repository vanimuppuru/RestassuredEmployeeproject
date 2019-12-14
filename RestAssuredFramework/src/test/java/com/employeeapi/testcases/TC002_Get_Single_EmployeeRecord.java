package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_EmployeeRecord extends TestBase {
	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void getSingleEmployee()

	{
		logger.info("************TC002_Get_Single_EmployeeRecord*************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);
	}

	@Test
	void checkResponseBody() {
		String responseBody = response.getBody().asString();
		// logger.info("ResponseBody==>"+responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);

	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode();
		logger.info("StatusCode==>" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@AfterClass
	void tearDown() {
		logger.info("****************Finished***********************");
	}

}
