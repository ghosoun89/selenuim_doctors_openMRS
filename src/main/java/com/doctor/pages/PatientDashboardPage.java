package com.doctor.pages;

import com.doctor.base.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PatientDashboardPage extends TestBase {
    public PatientDashboardPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[contains(@id ,'.vitals')]")
    WebElement captureVitals;
    @FindBy(id = "save-form")
    WebElement saveFormBtn;
    @FindBy(css = "#height > input")
    WebElement heightInput;
    @FindBy(id = "next-button")
    WebElement nextBtn;
    @FindBy(css = "#weight > input")
    WebElement weightInput;
    @FindBy(css = "#temperature > input")
    WebElement temperatureInput;
    @FindBy(css = "#heart_rate > input")
    WebElement pressInput;
    @FindBy(css = "#respiratory_rate > input")
    WebElement respiratoryRate;
    @FindBy(css = "#bp_systolic > input")
    WebElement bloodPressure;
    @FindBy(css = "#o2_sat > input")
    WebElement o2Percentage;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveBtn;
    @FindBy(className = "encounter-details")
    WebElement vitalParts;
    @FindBy(className = "view-details")
    WebElement showDetails;
    @FindBy(className = "encounter-summary-container")
    WebElement summaryDetails;
    @FindBy(className = "deleteEncounterId")
    WebElement deleteEncounter;
    @FindBy(css = "#delete-encounter-dialog button.confirm")
    WebElement confirmDeleteBtn;
//    @FindBy(className = "dropdown-name")
//    WebElement actionsDropDown;
//    @FindBy(xpath = "//*[text() = 'Delete patient']")
//    WebElement deletePatientOption;
    @FindBy(xpath = "//a[contains(@href, 'patient.page')]")
    WebElement patientPageLink;
    @FindBy(className = "toast-type-success")
    WebElement successDeleteMessage;
    @FindBy(className = "toast-item-close")
    WebElement closeMessage;

    public void getCaptureVital() throws InterruptedException {
        captureVitals.click();
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOf(saveFormBtn));
        heightInput.sendKeys("160");
        nextBtn.click();
        weightInput.sendKeys("60");
        nextBtn.click();
        nextBtn.click();
        temperatureInput.sendKeys("36");
        nextBtn.click();
        pressInput.sendKeys("99");
        nextBtn.click();
        respiratoryRate.sendKeys("33");
        nextBtn.click();

        Actions actions = new Actions(driver);
        bloodPressure.sendKeys("55");
        actions.sendKeys(Keys.TAB);
        actions.build().perform();
//        Thread.sleep(2000);
        bloodPressure.sendKeys(Keys.TAB);
//        Thread.sleep(2000);
        actions.sendKeys("30");
        actions.build().perform();
        nextBtn.click();
        o2Percentage.sendKeys("20");
        nextBtn.click();
        saveBtn.click();
    }

    public boolean isVitalReportDisplay(){
        return vitalParts.isDisplayed();
    }

    public boolean isVitalDetailsDisplayed(){
        showDetails.click();
        return  summaryDetails.isDisplayed();
    }

    public void deleteEncounterReport() throws InterruptedException {
        deleteEncounter.click();
        confirmDeleteBtn.click();
        Thread.sleep(2000);
    }
    public String getText(){
        return successDeleteMessage.getText();
    }
    public void closeMessage(){
        closeMessage.click();
    }
//    public void deletePatient(){
//        Actions actions = new Actions(driver);
//        actions.moveToElement(actionsDropDown).perform();
//        deletePatientOption.click();
//    }

    public PatientPage backToPatientPage(){
        patientPageLink.click();
        return new PatientPage();
    }
}
