package pageObjects;

import org.openqa.selenium.WebElement;

import utilities.WebElementLib;

public class SamplePageObjects {

	public WebElement signInBtn() {
		return WebElementLib.findMyElement("cssSelector", "body > header > div > div > div > a.button.button--medium.button--mobile-before-hero-only");
		
	}
	
//	choose Language
	public WebElement chooseLang() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"lang-chooser\"]/div[1]/div[1]/div[1]/span" );
		
	}
//	select English
	public WebElement chooseEng() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"lang-chooser\"]/div[2]/div[13]/span");
		
	}
	
	public WebElement enterEmail() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"identifierId\"]");
				
	}
	
	public WebElement enterEmailNext() {
		return WebElementLib.findMyElement("cssSelector", "#identifierNext > div > button > span");
				
	}
	
	public WebElement enterPassword() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"password\"]/div[1]/div/div[1]/input");
				
	}
	
	public WebElement enterPasswordNext() {
		return WebElementLib.findMyElement("cssSelector", "#passwordNext > div > button > span");
				
	}
	
	// compose email btn
	public WebElement compose() {
		return  WebElementLib.findMyElement("xpath", "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div");
	}
//	write email
	public WebElement sendTo() {
		return  WebElementLib.findMyElement("xpath", "//*[@id=\":px\"]");
	}
	public WebElement emailSubject() {
		return  WebElementLib.findMyElement("xpath", "//*[@id=\":pf\"]");
	}
	public WebElement emailBody() {
		return  WebElementLib.findMyElement("xpath", "//*[@id=\":qk\"]");
	}
	public WebElement sendBtn() {
		return  WebElementLib.findMyElement("xpath", "//*[@id=\":p5\"]");
	}
	
//	close the alert by clicking no thanks
	public WebElement noThanks() {
		return WebElementLib.findMyElement("cssSelector", "body > div.bjd.anN.ahP > div.bi9.bi6 > div.SQD3t > button.by7.T-I");
	}
	
	// select the first email
	public WebElement selectTopEmail() {
		return WebElementLib.findMyElement("cssSelector", "#\\:ss");
	}
	// view sent message
	public WebElement viewSentMsz() {
		return WebElementLib.findMyElement("cssSelector", "#link_vsm");
	}
}
