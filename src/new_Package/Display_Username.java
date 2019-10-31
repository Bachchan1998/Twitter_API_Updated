package new_Package;
import org.testng.annotations.Test;
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

		// To Display User Names those who have Tweeted message that containing Qualitest word.

	public class Display_Username 
{
	
		Properties prop = new Properties();
		Logger L=Logger.getLogger("Block_User");
	
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
			RestAssured.baseURI=prop.getProperty("search");
			Response res=given().auth().oauth(prop.getProperty("Consumerkey"), prop.getProperty("ConsumerSecretkey"), prop.getProperty("Token"), prop.getProperty("TokenSecretkey")).
					param("q","Qualitest").
					when().get("/tweets.json").then().assertThat().statusCode(200).and().extract().response();
        
			String response=res.asString();
			L.info(response);
			
			JsonPath js=new JsonPath(response);        
			int count=js.get("statuses.size()");
			L.info(response);
        
			for(int i=0;i<count;i++)
         {
				String user=js.get("statuses["+i+"].user.screen_name");
				L.info(user);
         }
     }	

}
