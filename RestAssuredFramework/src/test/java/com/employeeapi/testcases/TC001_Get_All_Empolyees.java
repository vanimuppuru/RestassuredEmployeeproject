package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Empolyees extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger.info("****************TC001_Get_All_Empolyees*******************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(5000);

	}

	@Test
	public void checkResponseBody() {

		logger.info("***********ResponseBody***********");
		String responseBody = response.getBody().asString();
		logger.info("ResponseBody---->" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	public void statusCode() {
		logger.info("*********Checking Statuscode********");
		int statusCode = response.getStatusCode();
		logger.info("Statuscode==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void chectStatusLine() {

		logger.info("************Checking StatusLine*************");

		String statusLine = response.getStatusLine();
		logger.info("StatusLine==> " + statusLine);
	}

	@Test
	public void checkContentType() {

		logger.info("**********Checking ContentType***********");
		String contentType = response.getContentType();
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	public void checkResponseTime() {
		logger.info("*************Checking ResponseTime**********");
		long time = response.getTime();
		logger.info("responsetime==>" + time);
		if (time > 2000)
			logger.warn("response time is >2000");
		Assert.assertTrue(time < 2000);

	}

	@AfterClass
	public void teardown() {

		logger.info("***********Finished TC001_Get_All_Empolyees.java*************");
	}

}
