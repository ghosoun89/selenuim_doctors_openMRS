package com.doctor.testcases;

import com.doctor.base.TestBase;
import com.doctor.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class PatientPageTests extends TestBase {
    HomePage homePage;
    PatientPage patientPage;
    PatientDashboardPage patientDashboardPage;
    RegisterPatientPage registerPatientPage;

    @BeforeMethod
    public void setUp(){
        launchBrowser();
        LoginPage loginPage = new LoginPage();
        homePage = loginPage.loginAsAdmin();

    }
    @Test
    public void getCaptureVitalTest() throws InterruptedException {
        registerPatientPage = homePage.clickOnRegisterPatient();
        patientPage = registerPatientPage.fillPatientInfo("lamees", "ahmad", "lolo", "Female", "12", "April", "2001", "Qatar", "+96278787878");
        Assert.assertTrue(patientPage.isPatentPageRender());
        patientDashboardPage = patientPage.startVisit();
        patientDashboardPage.getCaptureVital();
        Assert.assertTrue(patientDashboardPage.isVitalReportDisplay());
        Assert.assertTrue(patientDashboardPage.isVitalDetailsDisplayed());
        Assert.assertEquals(patientDashboardPage.getText(), "Entered Vitals for lamees ahmad lolo");
        patientDashboardPage.closeMessage();
        patientDashboardPage.deleteEncounterReport();
        Assert.assertEquals(patientDashboardPage.getText(), "Encounter has been deleted");
        patientPage = patientDashboardPage.backToPatientPage();
        patientPage.deletePatient();
        Assert.assertEquals(patientPage.getDeleteMessageText(), "Patient has been deleted successfully");
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
