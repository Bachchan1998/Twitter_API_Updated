package new_Package;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

	public class whether 
{
	
		Properties prop = new Properties();
		Logger L=Logger.getLogger("whether");
		
		@BeforeTest
		public void first() throws Exception
	 {
			PropertyConfigurator.configure("C:\\New folder2\\Twitter API-2\\data.properties");
			FileInputStream F=new FileInputStream("C:\\New folder2\\Twitter TestNG\\src\\new_Package\\twitter.properties");
			prop.load(F);
	 }
	
		
		@Test 
		public void listusers() 
	 {
			RestAssured.baseURI=prop.getProperty("weather");
			Response res=given().auth().oauth(prop.getProperty("Consumerkey"), prop.getProperty("ConsumerSecretkey"), prop.getProperty("Token"), prop.getProperty("TokenSecretkey")).
					param("q","bangalore weather").     
					when().get("/tweets.json").then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
		
			String response=res.asString();
			L.info(response);
     }
}