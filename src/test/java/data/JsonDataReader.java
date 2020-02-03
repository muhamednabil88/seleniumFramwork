package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





public class JsonDataReader {
	public String firstname , lastname , email , password;
	public void JsonReader() throws  IOException, ParseException {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\userregistrationData.json";
		File srcFile = new File(filePath);
		JSONParser parser = new JSONParser();
		JSONArray array = (JSONArray)parser.parse(new FileReader(srcFile));
		for(Object jsonObj : array) {
			JSONObject person = (JSONObject) jsonObj;
			firstname = (String) person.get("firstname");
			lastname = (String) person.get("lastname");
			email = (String) person.get("email");
			password = (String) person.get("password");
		}
	}
}
