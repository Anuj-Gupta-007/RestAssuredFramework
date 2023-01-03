package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification requestSpec;

	public String getGlobalValue(String baseUrlkey) throws IOException {
	
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Anuj.gupta3\\eclipse-workspace\\APIAuto\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(baseUrlkey);
	}
	
	
public RequestSpecification requestSpecification() throws IOException {
		
	if(requestSpec == null)
	{
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
		requestSpec = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key","qaclick123")
				.setContentType(ContentType.JSON)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return requestSpec;
	}
		else
			return requestSpec;
}

public String getJSONPath(Response response, String key) {
	String resp = response.asString();
	JsonPath jp = new JsonPath(resp);
	return jp.get(key).toString();
	 
}
}
