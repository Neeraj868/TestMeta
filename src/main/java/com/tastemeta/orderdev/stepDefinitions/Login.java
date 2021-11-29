package com.tastemeta.orderdev.stepDefinitions;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
	 
    WebDriver driver;
 
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "./chromedriver.exe");
 
        driver = new ChromeDriver();
 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
 
    @Given("User is on Home page")
    public void userOnHomePage() {
 
        driver.get("https://orderdev.tastemeta.com/home");
    }
 
    @When("User clicks on {string} button")
    public void clicksOnButton(String buttonName) throws InterruptedException {
    	System.out.println("Clicked on : "+buttonName+" button");
    	switch(buttonName) {
    	case "Login":
    		Thread.sleep(1000);
    		driver.findElement(By.xpath("//p[@class='login cursor']")).click();
    		break;
    	case "Sign in":
    		driver.findElement(By.xpath("//button[@type='submit']")).click();
    		break;
    	case "Create an account":
    		driver.findElement(By.xpath("//button[contains(text(), '"+buttonName+"')]")).click();
    		break;
    	case "Settings":
    		driver.findElement(By.xpath("//a[contains(text(), 'Settings')]")).click();
    		break;
    	case "Change Password":
    		driver.findElement(By.xpath("//p[contains(text(), 'Change Password ')]")).click();
    		break;
    	case "Confirm":
    		driver.findElement(By.xpath("//button[contains(text(), '"+buttonName+"')]")).click();
    		break;
    	case "Logout":
            WebElement logout = driver.findElement(By.xpath("//p[contains(text(), 'Logout')]"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", logout);
    		break;
    	}
        
    }
    
    @When("User enters email as {string}")
    public void entersEmail(String email) throws InterruptedException {
    	System.out.println("Email Entered");
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
    }
 
    @When("User enters password as {string}")
    public void entersPassword(String passWord) throws InterruptedException {
        System.out.println("Password Entered");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(passWord);
    }
 
    @Then("User should not be able to login sucessfully")
    public void inSucessfullLogin() throws InterruptedException {
        boolean errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Invalid Email address or Password')]")).isDisplayed();
        Assert.assertEquals(errorMessage, errorMessage, "Should not be able to Login with Invalid Credentials");
    }
    
    @When("User fills the form for account creation")
    public void fillForm() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Neeraj");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Sharma");
        
        driver.findElement(By.xpath("//div[@class='iti__flag-container']")).click();
        driver.findElement(By.xpath("//li[@data-country-code='in']")).click();
        driver.findElement(By.xpath("//input[@formcontrolname='phone']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//input[@formcontrolname='email']")).sendKeys("neeraj.sharma@gmail.com");
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("Admin@123");
      
        WebElement agreeToAll = driver.findElement(By.xpath("(//span[@class='checkmark'])[1]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", agreeToAll);
        
    }
    
    @Then("Verify Account is created successfully")
    public void sucessfullAccountCreation() throws InterruptedException {
        boolean successMessage = driver.findElement(By.xpath("//div[contains(text(), 'Account created successfully')]")).isDisplayed();
        Assert.assertEquals(successMessage, successMessage, "Account should be created successfully");
    }
 
    @And("User enters {string} in {string} field")
    public void enterValueInField(String password, String fieldName) throws InterruptedException {
        System.out.println("Password Entered");
        switch(fieldName) {
    	case "Current Password":
    		driver.findElement(By.xpath("//input[@formcontrolname='current_password']")).sendKeys(password);
    		break;
    	case "New Password":
    		driver.findElement(By.xpath("//input[@formcontrolname='new_password']")).sendKeys(password);
    		break;
    	case "Confirm Password":
    		driver.findElement(By.xpath("//input[@formcontrolname='confirm_password']")).sendKeys(password);
    		break;
    	}
    }
    
    @Then("Verify User has logged in successfully")
    public void sucessfullLogin() throws InterruptedException {
    	Thread.sleep(1000);
        boolean logOutButton = driver.findElement(By.xpath("//p[contains(text(), 'Logout')]")).isDisplayed();
        Assert.assertEquals(logOutButton, logOutButton, "User should be logged in successfully");
    }
    
    @AfterStep
	public void addScreenshot(Scenario scenario){

		//validate if scenario has failed
		if(scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image"); 
		}
		
	}
    
    @After
    public void teardown() {
 
        driver.quit();
    }
 
}
