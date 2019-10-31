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

				//To Fetch HASH TAG From Different Country 	

	public class HashTag
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
		public void get_Tweet() 
	{
			int[] k = {1,2295383,28218,23424977,23424852};
			
			for(int i=0;i<k.length;i++)
		 {
			RestAssured.baseURI=prop.getProperty("hashtag");
			Response res=given().auth().oauth(prop.getProperty("Consumerkey"), prop.getProperty("ConsumerSecretkey"), prop.getProperty("Token"), prop.getProperty("TokenSecretkey")).
					queryParam("id",k[i])
					.when().get("/place.json").then().extract().response();
		
		    String response=res.asString();
		    L.info(response);
		    
		    
		    JsonPath js=new JsonPath(response);
		    String name=js.get("name").toString();
		    L.info(name);
		    
	     }
	}
}