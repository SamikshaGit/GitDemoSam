package test;
import org.testng.annotations.Test;
import files.Payloads;
import io.restassured.path.json.JsonPath;

//6. Verify if Sum of all Course prices matches with Purchase Amount
public class SumValidation {
	@Test
	public void SumValidationMethod()
	{
		JsonPath js=new JsonPath(Payloads.CoursePrice());
				int countCours= js.getInt("courses.size()");
		int sum=0;
		for(int j=0; j<countCours; j++)
		{
			sum=sum+js.getInt("courses["+j+"].price")*js.getInt("courses["+j+"].copies");
			System.out.println("Sum of the courses is: "+sum);
		}
		if (sum==js.getInt("dashboard.purchaseAmount"))
		{
			System.out.println("Sum of all Course prices matches with Purchase Amount");
		}
		else {
			System.out.println("Sum of all Course prices does not match with Purchase Amount");
		}
	}

}
