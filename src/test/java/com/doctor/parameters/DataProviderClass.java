package com.doctor.parameters;

import com.doctor.pages.RegisterPatientPage;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviderClass {
    RegisterPatientPage registerPatientPage = new RegisterPatientPage();

    @DataProvider(name = "test-data")
    public Object[][] testData() throws IOException {
        Object data[][] = registerPatientPage.createData();
        return data;
    }

    public Object[] getColumnData() throws IOException {
        Object[][] array = testData();
        Object[] column = new Object[array.length];
        for(int i=0; i<column.length; i++){
            column[i] = array[i][0];
        }
        return column;
    }
}
