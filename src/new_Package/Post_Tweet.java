package new_Package;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

		// TO Post a Message In Twitter

	public class Post_Tweet 
	
{
		Properties prop = new Properties();
		Logger L=Logger.getLogger("Post_Tweet");
	  
		@BeforeTest
	    public void first() throws Exception
	 {		
			PropertyConfigurator.configure("C:\\New folder2\\Twitter API-2\\data.properties");
			FileInputStream F=new FileInputStream("C:\\New folder2\\Twitter TestNG\\src\\new_Package\\twitter.properties");
			prop.load(F);
	 }
		
	
		@Test
		public void Post_Tweet() 
	 {
			RestAssured.baseURI=prop.getProperty("url");
			Response res=given().auth().oauth(prop.getProperty("Consumerkey"), prop.getProperty("ConsumerSecretkey"), prop.getProperty("Token"), prop.getProperty("TokenSecretkey")).
					queryParam("status"," I a m  LEARNING API   TESTING  USING  REST ASSURED  JAVA #QUALITEST1 ")
					.when().post("/update.json").then().extract().response();
		
			String response=res.asString();
			L.info(response);
			
		
			JsonPath js=new JsonPath(response);
			String id=js.get("id").toString();
			L.info(id);
			
		
			String text=js.get("text").toString();
			L.info(text);
			
	 }	
}
