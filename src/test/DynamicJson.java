package test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
public class DynamicJson {
	@Test
	public void AddBook()
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String resp=given().log().all().body(Payloads.addBook("test5", "5")).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().extract().response().asString();
		System.out.println(resp);
		JsonPath js=new JsonPath(resp);
		String idNumber= js.get("ID");
		System.out.println(idNumber);
	}
	@DataProvider
	public Object getData()
	{
		return null;
		
		
		
	}

}
