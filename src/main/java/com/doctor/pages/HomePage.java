package com.doctor.pages;

import com.doctor.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(), 'Logout')]")
    WebElement logOutBtn;
    @FindBy(xpath = "//a[contains(@id , 'referenceapplication-registrationapp-registerPatient')]")
    WebElement registerPatientBtn;
    @FindBy(xpath = "//a[contains(@href, '/openmrs/coreapps/findpatient/')]")
    WebElement findPatientRecord;

    public boolean isElementVisible(){
        return logOutBtn.isDisplayed();
    }

    public RegisterPatientPage clickOnRegisterPatient(){
        registerPatientBtn.click();
        return new RegisterPatientPage();
    }

    public FindPatientPage clickOnFindPatient(){
        findPatientRecord.click();
        return new FindPatientPage();
    }

    public LoginPage getLogout(){
        logOutBtn.click();
        return new LoginPage();
    }
}
