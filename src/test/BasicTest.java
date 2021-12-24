package test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import files.Payloads;


public class BasicTest {
	public static void main(String[] args) {
		//Add a place
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
	body(Payloads.AddPlace()).
	when().post("/maps/api/place/add/json").
	then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	System.out.println(response);
	JsonPath js=new JsonPath(response);
	String placeID=js.getString("place_id");
	System.out.println("Place Id of the added place is: "+placeID);
	
   //Update the address of the place
	given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).
	body("{\r\n" + 
			"\"place_id\":\""+placeID+"\",\r\n" + 
			"\"address\":\"Pune\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}").
	when().put("/maps/api/place/update/json").
	then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
	//Validate the updated address 
	String getResp=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID).
	when().get("/maps/api/place/get/json").
	then().assertThat().log().all().statusCode(200).extract().response().asString();
	// We can assert this: body("address", equalTo("Pune"));
	System.out.println(getResp);
	JsonPath js1=new JsonPath(getResp);
	String addressPlace=js1.getString("address");
	System.out.println(addressPlace);
	Assert.assertEquals(addressPlace, "Pune");
	}
}
