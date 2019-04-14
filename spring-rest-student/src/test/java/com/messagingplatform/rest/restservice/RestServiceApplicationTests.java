package com.messagingplatform.rest.restservice;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateStudent() throws JSONException {
		JSONObject jsonObj = new JSONObject()
				.put("name","Me")
				.put("passportNumber","123");
		given()
				.contentType("application/json")
				.body(jsonObj.toString())
				.when()
				.post("/students")
				.then()
				.statusCode(201);
	}
}
