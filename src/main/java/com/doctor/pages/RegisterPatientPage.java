package com.doctor.pages;

import com.doctor.base.TestBase;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class RegisterPatientPage extends TestBase {
    public RegisterPatientPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='givenName']")
    WebElement patientName;
    @FindBy(css = "[name=\"middleName\"]")
    WebElement patientMiddleName;
    @FindBy(css = "[name=\"familyName\"]")
    WebElement patientFamilyName;
    @FindBy(id = "next-button")
    WebElement nextBtn;
    @FindBy(css = "[name=\"gender\"]")
    WebElement genderField;
    @FindBy(id = "birthdateDay-field")
    WebElement dayInput;
    @FindBy(id = "birthdateMonth-field")
    WebElement monthDropDown;
    @FindBy(id = "birthdateYear-field")
    WebElement yearInput;
    @FindBy(id = "address1")
    WebElement addressInput;
    @FindBy(css = "[name=\"phoneNumber\"]")
    WebElement phoneNumber;
    @FindBy(id = "submit")
    WebElement confirm;

    public Object[][] createData() throws IOException {
        File file = new File("src/main/java/com/doctor/testdata/PatientTestData.xlsx");
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int columns = sheet.getRow(0).getLastCellNum();
        Object data[][] = new Object[rows][columns];

        for(int i=0; i < rows; i++){
            for (int k=0; k < columns; k++ ){
              try {
                    data[i][k] = sheet.getRow(i+1).getCell(k).toString();
                    }catch (Exception e){
                        data[i][k] = "";
                //        System.out.println(e);
                    }
               }
        }
        workbook.close();
        return data;
    }

    public PatientPage fillPatientInfo(String name, String middle, String last, String gender, String day, String month, String year, String address, String phone){
        patientName.sendKeys(name);
        patientMiddleName.sendKeys(middle);
        patientFamilyName.sendKeys(last);
        nextBtn.click();

        selectFromDropDown(genderField, gender);
        nextBtn.click();
        dayInput.sendKeys(day);

        selectFromDropDown(monthDropDown, month);
        yearInput.sendKeys(year);
        nextBtn.click();
        addressInput.sendKeys(address);
        nextBtn.click();
        phoneNumber.sendKeys(phone);
        nextBtn.click();
        nextBtn.click();
        confirm.click();
        return new PatientPage();
    }

    public void selectFromDropDown(WebElement element, String item){
        Select dropDown = new Select(element);
        dropDown.selectByVisibleText(item);
    }


}
