

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseClass  {
	
	
	
	public void click_ChangePlan(AppiumDriver driver) {		
		WebElement btn_Change = driver.findElementByAccessibilityId("Change");	
		btn_Change.click();
	}
	public void questionnairePage(AppiumDriver driver) {
		//Locate main Questionnaire page
		WebElement element = driver.findElement(By.xpath("//android.webkit.WebView[@text='Questionnaire']"));					
		//Locate all element of Questionnaire page
		System.out.println("I am here: ");
		scrollEle(driver);
		
		WebElement dd = driver.findElement(By.xpath("//android.view.View[@text='Have you travelled outside of the country within the last 14 days? *']"));
		List<WebElement> childElement = dd.findElements(By.xpath("./*"));
		System.out.println(childElement.size());
/*
		List<WebElement> questions_Count =  element.findElements(By.xpath("//android.view.View"));

		int j = questions_Count.size();
		for (int i=0; i<j;i++){
			//System.out.println( i+"." +questions_Count.get(i).getAttribute("text"));
			//Click on NO
			for (int l=0; l<TestAppium.questionArray.size(); l++) {
				if(questions_Count.get(i).getAttribute("text").equals(TestAppium.questionArray.get(l).getQuestionText())) {
					if(questions_Count.get(i+1).getAttribute("text").equals(TestAppium.questionArray.get(l).getAns())) {
						questions_Count.get(i+1).click();
					}else {
						questions_Count.get(i+2).click();
					}
					scrollEle(driver);
					// Find K to avoid Index out of Bound Error
					int k = questions_Count.size()-i;
					// If condition K>3 to avoid continuous looping and K<=10 to find new element after scrolling
					if(k<=10 && k>3) {
						scrollEle(driver);									
						questions_Count =  element.findElements(By.xpath("//android.view.View"));
						//System.out.println("new Question Count size: "+questions_Count.size());
						j=questions_Count.size();
						i =0;
					}
					
				}
				i = i+2;
				
			}
			
		}
		
		*/
		
		WebElement btn_Submit = driver.findElement(By.xpath("//android.widget.Button[@text='Submit']"));
		btn_Submit.click();
	}
	public void scrollEle(AppiumDriver driver) {
		TouchAction  action;
		Dimension size = driver.manage().window().getSize();	
		int anchor = (int) (size.width / 2);
		int startPoint = (int) (size.getHeight()*0.4);
		int endPoint = (int) (size.getHeight()*0.2);
		System.out.println("startpoint:"+startPoint+" and Endpoint: "+endPoint);
		int tempPoint = startPoint;
		startPoint = endPoint;
		endPoint = startPoint;
		
		System.out.println("startpoint:"+startPoint+" and Endpoint: "+endPoint);
		new TouchAction((PerformsTouchActions) driver)
		 .press(PointOption.point(anchor, startPoint))
		 .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2500)))
		 .moveTo(PointOption.point(anchor, endPoint))
		 .release().perform(); 
	
/*		new TouchAction((PerformsTouchActions)driver)
		.press(PointOption.point(0, startPoint))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(15)))
		.moveTo(PointOption.point(0, endPoint))
		.release().perform();
		action.press(PointOption.point(0, startPoint))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10)))
		.moveTo(PointOption.point(0, endPoint)).release().perform();
	*/	
	} 	 
	
	public void switch_Toggle(AppiumDriver driver) {
		WebElement switchToggle = driver.findElement(By.className("android.widget.Switch"));
		switchToggle.click();
		
	}

	public void office_Address_Dropdown(AppiumDriver driver) {
		WebElement btn_Dropdown_Element = driver.findElement(By.xpath("//android.widget.Button[@index='6']"));
		btn_Dropdown_Element.click();		
	}
	
	public void click_SaveChanges(AppiumDriver driver) {
		WebElement btn_SaveChanges = driver.findElementByAccessibilityId("Save Changes");
		btn_SaveChanges.click();
	}

	
}

