package com.doctor.pages;

import com.doctor.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindPatientPage extends TestBase {
    public FindPatientPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id= "patient-search")
    WebElement searchInput;
    @FindBy(css = ".odd")
    WebElement searchResult;

    public PatientPage searchForPatient(String patientName) throws InterruptedException {
        searchInput.sendKeys(patientName);
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOf(searchResult)).click();
        return new PatientPage();
//        Thread.sleep(2000);
//        searchResult.click();
    }

}
