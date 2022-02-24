package com.doctor.pages;

import com.doctor.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {
    public LoginPage(){
//        super();
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "username")
    WebElement userNameInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(xpath = "//li[@id='Inpatient Ward']")
    WebElement sessionLocation;
    @FindBy(id = "loginButton")
    WebElement loginBtn;
    @FindBy(id = "login-form")
    WebElement loginForm;

    public HomePage loginAsAdmin(){
        userNameInput.sendKeys(props.getProperty("userName"));
        passwordInput.sendKeys(props.getProperty("password"));
        sessionLocation.click();
        loginBtn.click();
        return new HomePage();
    }

    public boolean isLoginFormDisplay(){
        return loginForm.isDisplayed();
    }
}
