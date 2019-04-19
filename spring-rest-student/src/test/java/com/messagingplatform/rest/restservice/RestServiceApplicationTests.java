package com.messagingplatform.rest.restservice;

import com.messagingplatform.rest.restservice.entity.Student;
import io.restassured.RestAssured;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
		SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceApplicationTests {

	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
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

	@Test
	public void testGetAllStudents() throws JSONException {
		given()
				.when()
				.get("/students")
				.then()
				.statusCode(200);
	}

	@Test
	public void testGetStudent() throws JSONException {
		Student student = given()
				.pathParam("id", "10001")
				.when().get("/students/{id}")
				.then()
				.statusCode(200)
				.extract()
				.as(Student.class);

		assertEquals("Ranga", student.getName());
	}

	@Test
	public void testPutStudent() throws JSONException {
		JSONObject jsonObj = new JSONObject()
				.put("name","Me")
				.put("passportNumber","123");
		given()
				.pathParam("id", "10001")
				.contentType("application/json")
				.body(jsonObj.toString())
				.when().put("/students/{id}")
				.then()
				.statusCode(200);
	}
}
