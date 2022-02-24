package com.doctor.testcases;

import com.doctor.base.TestBase;
import com.doctor.pages.HomePage;
import com.doctor.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests extends TestBase {
//    public LoginPageTests(){
//        super();
//    }

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        launchBrowser();
        loginPage = new LoginPage();

    }

    @Test
    public void loginSuccessfullyTest(){
        homePage = loginPage.loginAsAdmin();
        Assert.assertTrue(homePage.isElementVisible());
    }

    @Test
    public void LogoutTest(){
        homePage = loginPage.loginAsAdmin();
        loginPage = homePage.getLogout();
        Assert.assertTrue(loginPage.isLoginFormDisplay());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
