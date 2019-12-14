package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.internal.org.jline.utils.Log;

public class TC003_Post_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;
	String empname = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empage = RestUtils.empAge();

	@BeforeClass
	public void createEmployee() throws InterruptedException {

		logger.info("******************TC003_Post_Employee_Record************");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empname);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empage);

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(5000);
	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		logger.info("ResponseBody==>" + responseBody);
		Assert.assertTrue(responseBody.contains(empname));
		Assert.assertTrue(responseBody.contains(empSalary));
		Assert.assertTrue(responseBody.contains(empage));
	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void checkStatusLine() {

		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	@Test
	void checkContentType() {
		String contentType = response.contentType();
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@AfterClass
	void tearDown() {
		logger.info("***********Finished Creating New EmloyeeRecords*********");
	}

}
