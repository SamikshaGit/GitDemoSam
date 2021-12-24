package test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetPlace {

	public static void main(String[] args) {
	
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").queryParam("placeid", "0a77753be7760a515792fdf3d7b8c564").header("Content-Type", "application/json").
		when().get("/maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200);

	}

}
