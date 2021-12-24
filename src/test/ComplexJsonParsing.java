package test;

import files.Payloads;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParsing {
	
	public static void main(String[] args) {
	JsonPath js=new JsonPath(Payloads.CoursePrice());

   //1. Print No of courses returned by API
	int countCours=js.getInt("courses.size()");
	System.out.println(countCours);
	
	//2.Print Purchase Amount
	String purchaseAmount=js.getString("dashboard.purchaseAmount");
	System.out.println(purchaseAmount);
	
	//3. Print Title of the first course
	String firstCourseTitle=js.getString("courses[0].title");
	System.out.println(firstCourseTitle);
	
	//4. Print All course titles and their respective Prices
	for(int i=0; i<countCours; i++)
	{
		System.out.print("Name of the Course is : "+js.getString("courses["+i+"].title"));
		System.out.println("  And the Price of the course is : "+js.getInt("courses["+i+"].price"));
	}
	
	//5. Print no of copies sold by RPA Course
	System.out.println("No of copies sold by RPA Course");
	for(int j=0; j<countCours; j++)
	{
		String title=js.getString("courses["+j+"].title");
		if(title.equalsIgnoreCase("RPA"))
		{
			System.out.println(js.getString("courses["+j+"].copies"));
			break;
		}
		
	}
	
	//6. Verify if Sum of all Course prices matches with Purchase Amount
	int sum=0;
	for(int j=0; j<countCours; j++)
	{
		sum=sum+js.getInt("courses["+j+"].price");
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
