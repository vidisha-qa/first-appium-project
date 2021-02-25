

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class Login_TestCases {
	
	WebElement btn_Sign_In;
	WebDriverWait wait ;
	AppiumDriver driver;
	
	public Login_TestCases(AppiumDriver driver) {
		this.driver = driver;
	}
	
	
	public void prodSignIn() {
		
		btn_Sign_In = driver.findElementByAccessibilityId("Sign In");
		btn_Sign_In.click();
		WebElement webView = driver.findElement(By.className("android.webkit.WebView"));
		switchTo_Webview();	
		WebElement ele_emailText =  driver.findElement(By.xpath("//*[@id=\"form-username-container\"]/input"));
		ele_emailText.sendKeys("vidisha@afreespace.com");
		WebElement ele_NextButton = driver.findElement(By.xpath("//*[@id=\"login-nxt-btn\"]"));
		ele_NextButton.click();
		WebElement loader = driver.findElement(By.xpath("//*[@id=\"loader\"]/div"));
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.invisibilityOf(loader));		
		WebElement microsoft_Login = driver.findElement(By.xpath("//*[@id=\"tilesHolder\"]/div[1]/div/div"));
		microsoft_Login.click();
		switchTo_NativeApp();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		//WebElement scrollView =   driver.findElement(By.xpath("//android.widget.ScrollView"));
		//WebElement lblName = scrollView.findElement(By.xpath("//android.view.View[@index='2']"));
		//for(WebElement dd : lblName ) 
		//	System.out.println(dd.getAttribute("content-desc"));
		
		//Assert.assertEquals(lblName.getAttribute("content-desc"), "Vidisha Shedge");
	}
	
	public void signIn() {
		
		btn_Sign_In = driver.findElementByAccessibilityId("Sign In");
		btn_Sign_In.click();
		WebElement webView = driver.findElement(By.className("android.webkit.WebView"));
		switchTo_Webview();	
		
		driver.findElement(By.cssSelector(".form-control.form-control-lg")).sendKeys("21984"); //Sending User-id
		driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.btn-block")).click(); //Click on Get-user button
		//Explicit wait for loader to get invisible
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner-border.text-primary")));
		driver.findElement(By.cssSelector(".btn.btn-lg.btn-success.btn-block")).click(); //Final Submit of Mock Sign-In Page
//		driver.findElement(By.id("login-nxt-btn")).click();
		switchTo_NativeApp();
		
		//-----------------Verify--------
	//WebElement scrollView =   driver.findElement(By.xpath("//android.widget.ScrollView"));
	//WebElement lblName = scrollView.findElement(By.xpath("//android.view.View[@index='2']"));
	//for(WebElement dd : lblName ) 
	//	System.out.println(dd.getAttribute("content-desc"));
	
	//Assert.assertEquals(lblName.getAttribute("content-desc"), "Vidisha Shedge");
	}
	
	public void switchTo_Webview() {
		//Call getContext() method which will returns a list of contexts we can access, like 'NATIVE_APP' or 'WEBVIEW_1'
		Set<String> availableContexts = driver.getContextHandles();
		System.out.println("Total No of Context Found After we reach to WebView = "+ availableContexts.size());
		for(String context : availableContexts) {
			if(context.contains("WEBVIEW")){
				System.out.println("Context Name is " + context);
				//  Call context() method with the id of the context you want to access and change it to WEBVIEW_1
				//(This puts Appium session into a mode where all commands are interpreted as being intended for automating the web view)

				driver.context(context);
				break;
			}
		}
	}

	public void switchTo_NativeApp() {
		// 4.4 To stop automating in the web view context we can simply call the context again with id NATIVE_APP.
		Set<String> availableContexts = driver.getContextHandles();  
		for(String context : availableContexts) {
			if(context.contains("NATIVE")){
				System.out.println("We are Back to " + context);
				driver.context(context);
			}
		}
	}

}
