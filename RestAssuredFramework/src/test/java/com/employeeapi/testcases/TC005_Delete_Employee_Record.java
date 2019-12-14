package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	public void deleteEmployeeRecord() throws InterruptedException {

		logger.info("**************Deleting_Employee_Record******");
		RestAssured.baseURI = "http://restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");

		JsonPath jsonPathEvaluator = response.jsonPath();

		String empid = jsonPathEvaluator.get("[0].id");
		logger.info(empid);

		response = httpRequest.request(Method.DELETE, "/delete/" + empid);
		Thread.sleep(5000);

	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody != null);
		// Assert.assertEquals(responseBody.contains("{"success":{"text":"successfully!
		// deleted Records"}}", true);
	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		logger.info("StatusCode==>" + statusCode);
		Assert.assertEquals(statusCode, 405);

	}

	@Test
	void checkContentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@AfterClass
	public void teardown() {
		logger.info("********Finish_Deleting_Record********");
	}

}
