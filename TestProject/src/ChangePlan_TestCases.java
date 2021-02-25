

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ChangePlan_TestCases {
	WebDriverWait wait ;
	AppiumDriver myDriver;

	public ChangePlan_TestCases(AppiumDriver driver) {
		this.myDriver = driver;
	}

	BaseClass obj_Base = new BaseClass();
	public void questionnairePage() {
		//Locate main Questionnaire page.
		wait = new WebDriverWait(myDriver,50);

		WebElement mainPage = myDriver.findElement(By.xpath("//android.webkit.WebView"));
		wait.until(ExpectedConditions.visibilityOf(mainPage));

		WebElement element = myDriver.findElement(By.xpath("//android.webkit.WebView[@text='Questionnaire']"));		
		wait.until(ExpectedConditions.visibilityOf(element));
		scrollEle();
		List<WebElement> childLists = element.findElements(MobileBy.xpath("/*//*[2]//*"));
		System.out.println(childLists.size());
		for (int n =0 ; n<childLists.size(); n++) {
				System.out.println(n+ " " +childLists.get(n).getAttribute("text"));
		}
		//WebElement dd = myDriver.findElement(By.xpath("//android.view.View[@text='Have you travelled outside of the country within the last 14 days? *']"));
		//WebElement childElement = dd.findElement(By.xpath("sibling::@text='YES'"));
		
		//System.out.println(childElement.get(0).getAttribute("text"));
		
		MobileElement parentElement = (MobileElement) myDriver.findElement(By.xpath("//android.webkit.WebView[@text='Questionnaire']"));
		System.out.println("Loop to fetch children's of questionnaire");
		for (int vik=0; vik<10; vik++) {
			
			List<MobileElement> childList = parentElement.findElements(MobileBy.xpath("/."));
			if (childList.size()==2) {
				System.out.println("the required element found at : " + vik);
				break;
			}else if (childList.size() > 2) {
				System.out.println("required element has been missed : " + vik);
			}
			System.out.println(childList.size());
			System.out.println(parentElement.getAttribute("text"));
			System.out.println(childList.get(0).getAttribute("text"));
			System.out.println(childList.get(0).getAttribute("bounds"));
			parentElement = null;
			parentElement = childList.get(0);
			
		}
		System.out.println("end of test for loop");
		
		WebElement childView1 = element.findElements(By.xpath("/*")).get(0);
		WebElement childView2 = childView1.findElements(By.xpath("/*")).get(1);
		WebElement childView3 = childView2.findElements(By.xpath("/*")).get(0);
		WebElement childView4 = childView3.findElements(By.xpath("/*")).get(0);
		
		List<WebElement> myQuestions = childView1.findElements(By.xpath("/*"));
		System.out.println("Questions count: " + myQuestions.size());
//		List<WebElement> myQuestions = element.findElements(By.xpath("/*/*/*")).get(1).findElements(By.xpath("/*/*/*/*")).get(0).findElements(By.xpath("/*"));
		
		for(WebElement loopElement: myQuestions) {
			System.out.println(loopElement.findElements(By.xpath("/*")).get(0).getAttribute("text")); // this question to be matched in if condition
			loopElement.findElements(By.xpath("/*")).get(1); 
			// this could be either Yes or No button, should be checked by description or text and then clicked
			//alternatively could be Question object value can be passed
			
			loopElement.findElements(By.xpath("/*")).get(1); // this could left over button from yes or no
		}
		
		System.out.println("Wait here for debuggine");
		//.get(0).findElements(By.xpath("/*")).get(1).findEltements(By.xpath("/*/*"))
		//Locate all element of Questionnaire page
		scrollEle();
		List<WebElement> questions_Count =  myDriver.findElements(By.xpath("//android.view.View"));
		
		int j = questions_Count.size();
		for (int i=0; i<j;i++){
			System.out.println( i+"." +questions_Count.get(i).getAttribute("text"));
			//Click on NO
			if(questions_Count.get(i).getAttribute("text").equals("NO")) {
				questions_Count.get(i).click();
				scrollEle();
				// Find K to avoid Index out of Bound Error
				int k = questions_Count.size()-i;
				// If condition K>3 to avoid continuous looping and K<=10 to find new element after scrolling
				if(k<=10 && k>3) {
					scrollEle();									
					questions_Count =  element.findElements(By.xpath("//android.view.View"));
					//System.out.println("new Question Count size: "+questions_Count.size());
					j=questions_Count.size();
					i =0;
				}
			}
		}
		WebElement btn_Submit = myDriver.findElement(By.xpath("//android.widget.Button[@text='Submit']"));
		btn_Submit.click();
	}

	public void scrollEle() {
		TouchAction  action;
		//TouchAction  action =new TouchAction(myDriver);
		Dimension size = myDriver.manage().window().getSize();	
		int anchor = (int) (size.width / 2);
		int startPoint = (int) (size.getHeight()*0.4);
		int endPoint = (int) (size.getHeight()*0.2);
		System.out.println("startpoint:"+startPoint+" and Endpoint: "+endPoint);
		
		/*		int tempPoint = startPoint;
		startPoint = endPoint;
		endPoint = tempPoint;
		 *///		System.out.println("startpoint:"+startPoint+" and Endpoint: "+endPoint);

		/* new TouchAction((PerformsTouchActions) myDriver)
		.press(PointOption.point(0, startPoint))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(15)))
		.moveTo(PointOption.point(0, endPoint))
		.release().perform();
		 */
			new TouchAction((PerformsTouchActions) myDriver)
		 .press(PointOption.point(anchor, startPoint))
		 .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
		 .moveTo(PointOption.point(anchor, endPoint))
		 .release().perform(); 
		/*		action.press(PointOption.point(0, startPoint))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10)))
		.moveTo(PointOption.point(0, endPoint)).release().perform();
		  */	

		
	} 	
	

	public String check_UserStatus() {
		wait = new WebDriverWait(myDriver,50);
		MobileElement lblStatus =  (MobileElement) myDriver.findElement(By.xpath("//android.view.View[@index ='5']"));
		wait.until(ExpectedConditions.visibilityOf(lblStatus));
		String lbl_Today_UserStatus = lblStatus.getAttribute("content-desc");
		System.out.println("content-desc: " + lbl_Today_UserStatus);
		return lbl_Today_UserStatus;
	}

	public void changePlan_to_WFO() throws InterruptedException {
		String lbl_Today_UserStatus = check_UserStatus();
		System.out.println("First : User's Today's status is :"+lbl_Today_UserStatus);
//		scrollEle();
		obj_Base.click_ChangePlan(myDriver);
		obj_Base.switch_Toggle(myDriver);
		obj_Base.click_SaveChanges(myDriver);
		questionnairePage();
		//wait.until(ExpectedConditions.visibilityOf(lblStatus));

		WebElement ele_successful=  myDriver.findElement(By.xpath("//android.view.View[@text='Submission Successful']"));
//		Assert.assertEquals(ele_successful.isDisplayed(), true);
		WebElement btn_Check_in = myDriver.findElementByAccessibilityId("Check-In");
//		Assert.assertEquals(btn_Check_in.isDisplayed(), true);
		String newlbl_TodayUserStatus = check_UserStatus();
//		Assert.assertEquals(newlbl_TodayUserStatus, "Work from Office");				
	}	

	public void changePlan_to_WFH() {
		String lbl_Today_UserStatus = check_UserStatus();
		System.out.println("now User Status is : " +lbl_Today_UserStatus);
		obj_Base.click_ChangePlan(myDriver);
		obj_Base.switch_Toggle(myDriver);
		obj_Base.click_SaveChanges(myDriver);
		
		String new_changed_UserStatus = check_UserStatus();
		System.out.println("new status: " + new_changed_UserStatus);
//		Assert.assertEquals(new_changed_UserStatus.equals("Work from Home"),true );
	}
	
}

