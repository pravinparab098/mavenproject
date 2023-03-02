package pravinParab.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String,String>> getJsonDataToMap() throws IOException
		
	{
		//reading json File and store the data in the String called jsonContent 
		//while reading jsonContent it will asking the in what encoding format you want to write the String
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\pravinParab\\data\\purchaseOrder.json"),StandardCharsets.UTF_8);
		
		//convert the data from jsonContent String to the HashMap with the help of Jackson Databinder 
		/*
		 * we create a object as ObjectMapper and the functionas of that object is store in the mapper
		 * with the help of the mapper we read the value store in the jsonContentString
		 * the values from the jsonContent is again store in the data which is in List<HashMap<String, String>> format 
		 * so this data contains two object in the form of HashMap (Object[][] { { zara }, { iphone } };)
		 */
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
		
		/*
		 * now go to test file and call this data
		 */
	}
}
