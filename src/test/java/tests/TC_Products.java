package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;



public class TC_Products {


	
	@Test (priority = 1)

	public void getAllProducts ()

	{
		given()
		.when()
		.get("https://dummyjson.com/products")
		.then()
		.statusCode(200) ;

	}
	

	
	@Test(priority = 2)

	public void getSingleProduct ()

	{
		given()
		.when()
		.get("https://dummyjson.com/products/1")
		.then()
		.statusCode(200)
		.log().body() ;

	}

	

	@Test(priority = 3)

	public void searchProducts ()

	{
		given()
		.when()
		.get("https://dummyjson.com/products/search?q=Laptop")
		.then()
		.statusCode(200)
		.log().body() ;

	}


	
	@Test(priority = 4)

	public void addProduct ()

	{
		HashMap <String, String> data = new HashMap <String, String> () ;

		data.put("title", "OPPO F11 PRO")		;
		data.put("description", "latest smart phone from OPPO")		;
		data.put("price", "10,000 EGP")		;
		data.put("rating", "4.0")		;
		data.put("stock", "50")		;
		data.put("brand", "OPPO")		;
		data.put("category", "smart phone")		;


		Response res =


				given()
				.contentType("application/json")
				.body(data)


				.when()
				.post("https://dummyjson.com/products/add")


				.then()
				.statusCode(200)
				.log().body()
				.extract().response() ;


		String jsonString = res.asString() ;
		assertTrue(jsonString.contains("OPPO F11 PRO")) ;

	}


	@Test (priority = 5)

	public void updateProduct ()

	{

		HashMap <String, String> data = new HashMap <String, String> () ;

		data.put("id", "1")		;	
		data.put("title", "Samsung S2")		;
		data.put("description", "latest smart phone from Samsung")		;
		data.put("price", "12,000 EGP")		;
		data.put("rating", "4.5")		;
		data.put("stock", "40")		;
		data.put("brand", "Samsung")		;
		data.put("category", "smart phone")		;

		given()
		.contentType("application/json")
		.body(data) 

		.when()
		.put("https://dummyjson.com/products/1")

		.then()
		.statusCode(200)
		.log().body()

		.body("title", equalTo("Samsung S2"))
		.body("price", equalTo("12,000 EGP")) ;		

	}


	@Test (priority = 6)

	public void deleteProduct ()

	{
		Response res = 

				given()
				.when()
				.delete("https://dummyjson.com/products/1")

				.then()
				.statusCode(200) 
				.log().body() 
				.extract().response() ;


		String jsonString = res.asString ()  ;

		assertTrue(jsonString.contains("deletedOn")) ;

	}



}
