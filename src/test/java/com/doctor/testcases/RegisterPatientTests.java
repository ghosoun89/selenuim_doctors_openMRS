package com.doctor.testcases;

import com.doctor.base.TestBase;
import com.doctor.pages.*;
import com.doctor.parameters.DataProviderClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegisterPatientTests extends TestBase {
//    public RegisterPatientTests(){
//        super();
//    }

    FindPatientPage findPatientPage;
    HomePage homePage;
    PatientPage patientPage;
    RegisterPatientPage registerPatientPage = new RegisterPatientPage();

    @BeforeMethod
    public void setUp(){
        launchBrowser();
        LoginPage loginPage = new LoginPage();
        homePage = loginPage.loginAsAdmin();
    }

    @Test(priority = 1,dataProvider = "test-data", dataProviderClass = DataProviderClass.class)
    public void registerNewPatientTest(String name, String middle, String last, String gender, String day, String month, String year, String address, String phone) throws InterruptedException {
        registerPatientPage = homePage.clickOnRegisterPatient();
        patientPage = registerPatientPage.fillPatientInfo(name, middle, last, gender, day, month, year, address, phone);
        Assert.assertTrue(patientPage.isPatentPageRender());
        String url =  driver.getCurrentUrl();
        Assert.assertTrue(url.contains("patient.page"));
    }

    @Test(priority = 2)
    public void deletePatient() throws IOException, InterruptedException {
        findPatientPage = homePage.clickOnFindPatient();
        DataProviderClass dataProviderClass = new DataProviderClass();
        Object [] array = dataProviderClass.getColumnData();
        for(int i = 0; i < array.length; i++) {
            patientPage = findPatientPage.searchForPatient(array[i].toString());
            patientPage.deletePatient();
            String successMessage = patientPage.getDeleteMessageText();
            Assert.assertEquals(successMessage, "Patient has been deleted successfully");
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
