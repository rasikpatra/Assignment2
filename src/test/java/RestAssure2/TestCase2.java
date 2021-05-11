package RestAssure2;

import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestCase2 {

	@Test
	public void multiplyWSDL() throws Exception{
		FileInputStream f=new FileInputStream(".\\JSONFiles\\Multiply.xml");
		RestAssured.baseURI="http://www.dneonline.com";
		Response response=given().header("content-type","text/xml").
		       body(IOUtils.toString(f,"UTF-8"))
		       .post("/calculator.asmx").
		then(). 
				statusCode(200).assertThat().statusCode(200).log().all().extract().response();

		XmlPath xmlPath=new XmlPath(response.asString());
		String result=xmlPath.getString("MultiplyResult");
		Assert.assertEquals("20", result);
	}

}