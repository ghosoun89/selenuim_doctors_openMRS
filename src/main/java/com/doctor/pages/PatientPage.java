package com.doctor.pages;

import com.doctor.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PatientPage extends TestBase {
    public PatientPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[contains(@id , '.deletePatient')]")
    WebElement deletePatient;
    @FindBy(id = "delete-reason")
    WebElement deleteReasonMessage;
    @FindBy(css = "#delete-patient-creation-dialog >.dialog-content > button.confirm.right")
    WebElement confirmDeletePatientBtn;
    @FindBy(className = "patient-header")
    WebElement patientHeader;
    @FindBy (className = "toast-type-success")
    WebElement successDeleteMessage;
    @FindBy(xpath = "//a[@id= 'org.openmrs.module.coreapps.createVisit']")
    WebElement startVisit;
    @FindBy(id = "start-visit-with-visittype-confirm")
    WebElement startVisitConfirmBtn;


    public void deletePatient(){
        deletePatient.click();
        deleteReasonMessage.sendKeys("got heal");
        confirmDeletePatientBtn.click();
    }

    public boolean isPatentPageRender(){
        return patientHeader.isDisplayed();
    }

    public String getDeleteMessageText(){
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        return wait.until(ExpectedConditions.visibilityOf(successDeleteMessage)).getText();
    }

    public PatientDashboardPage startVisit(){
        startVisit.click();
        startVisitConfirmBtn.click();
        return  new PatientDashboardPage();
    }
}
