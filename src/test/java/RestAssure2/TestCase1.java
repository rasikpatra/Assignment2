package RestAssure2;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestCase1 {

	@Test(dataProvider ="dataProvider1")
	public void postTeacher(String firstname,String lastname,String stream){
		RestAssured.baseURI="http://localhost:3000";
		JSONObject json=new JSONObject();
		json.put("firstname", firstname);
		json.put("lastname", lastname);
		json.put("stream", stream);

		Response response=given().contentType(ContentType.JSON).body(json.toJSONString()).
		post("/students").
		then().
		statusCode(201).log().all().assertThat().statusCode(201).extract().response();
		String fname=response.jsonPath().getString("firstname");
		Assert.assertEquals(firstname, fname);
	}

	@DataProvider(name="dataProvider1")
	public Object[][] getData() throws Exception{
		Object[][] data=DataUtil.getTestdata();
		return data;
	}
}
