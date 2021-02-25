import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.lang.reflect.Type;
//import com.google.gson.reflect.TypeToken;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
//import jdk.internal.org.objectweb.asm.TypeReference;

public class TestAppium {

	public  static AppiumDriver driver;
	private static String appiumPort = "4723";
	private static String serverIp = "0.0.0.0";	
	public static List<Question> questionArray = new ArrayList<Question>();

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.out.println("Hello World");

		File fl = new File("myFile");
		/*
		BufferedWriter writer = new BufferedWriter(new FileWriter(fl, true));
		writer.write("Hello , I have written in a file");
		writer.close();
		 */

		BufferedReader br;
		String fileContent = "";
		try {
			br = new BufferedReader(new FileReader(fl));

			String currentLine;
			while((currentLine = br.readLine()) != null) {
				//System.out.println(currentLine);
				fileContent += currentLine;
			}
			//			System.out.println(fileContent);
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		Gson gson = new Gson();
		//		JsonObject obj = new JsonObject();
		//		JsonArray qtnArray = obj.getAsJsonArray(fileContent);

		JsonParser parser = new JsonParser();
		JsonArray jsonArray  = (JsonArray) parser.parse(fileContent); //questionArray

		//		final ObjectMapper objectMapper = new ObjectMapper();
		//		Question[] questionArray = objectMapper.readValue(fileContent, Question[].class);


		Iterator<JsonElement> iterator = jsonArray.iterator();
		while(iterator.hasNext()) {
			String jsonString = iterator.next().toString();
			System.out.println(jsonString);
			Question qtn = gson.fromJson(jsonString, Question.class);
			questionArray.add(qtn);
		}

		for (Question qtn : questionArray) {
			System.out.println(qtn.getLocation() + ", " + qtn.getAns());
		}
		//		System.out.println(qtnArray.toString());

		File appDir = new File( "C:\\Apps\\Test");
		File app = new File(appDir, "app-release.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", "OnePlus 7");	//One Plus 7
		//capabilities.setCapability("deviceName", "POCO F1");	//POCO F1
		//capabilities.setCapability("platformVersion", "10");	//One Plus 7
		capabilities.setCapability("platformVersion", "10");	//POCO F1
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.afreespace.employee");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.UDID, "15bb6517");	//OnePlus 7
		//capabilities.setCapability(MobileCapabilityType.UDID, "b5969ab");	//Poco F1
		//capabilities.setCapability("unicodeKeyboard", "true");                                     
		//capabilities.setCapability("resetKeyboard", "true");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
		String serverUrl = "http://" + serverIp + ":" + appiumPort + "/wd/hub";

		System.setProperty("webdriver.chrome.driver","C:/Users/VidishaShedge/AU/ChromeDriver/chromedriver.exe");
		System.out.println("Argument to driver object : " + serverUrl);
		try {
			driver =  new AppiumDriver(new URL(serverUrl), capabilities);
			System.out.println(" Server is up and installing apk");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} 
		catch ( MalformedURLException e) {
			throw new RuntimeException("appium driver could not be initialised for device ");
		}

		Login_TestCases objLogin = new Login_TestCases(driver);
		objLogin.signIn();

		ChangePlan_TestCases objChangePlan = new ChangePlan_TestCases(driver);
		try {
			objChangePlan.changePlan_to_WFO();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objChangePlan.changePlan_to_WFH();

	}

}
