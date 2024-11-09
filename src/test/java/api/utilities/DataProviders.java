package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //Data provider 1
    @DataProvider(name="Data")
    public String [][] getAllData() throws IOException {

        String path=".\\testData\\UserData.xlsx";   //taking xl file from testData directory

        ExcelUtils xlUtil = new ExcelUtils(path);  // creating an object from ExcelUtils Class

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalCols = xlUtil.getCellCount("Sheet1",1);

        String[][] apiData = new String[totalRows][totalCols]; // creating 2 dimension array which can store data

        for(int r=1;r<=totalRows;r++){  // 1  // Read data form xl file storing them in 2 dimension array
            for (int c=0;c<totalCols;c++){    // r for row c for cols
                apiData[r-1][c] = xlUtil.getCellData("Sheet1",r,c);
            }
        }
        return apiData;
    }
    @DataProvider(name="UserNames")
    public String [] getUserNames() throws IOException {

        String path=".\\testData\\UserData.xlsx";   //taking xl file from testData directory

        ExcelUtils xlUtil = new ExcelUtils(path);  // creating an object from ExcelUtils Class

        int totalRows = xlUtil.getRowCount("Sheet1");

        String [] apiData  = new String[totalRows]; // creating 1 dimension array which can store data

        for(int r=1;r<=totalRows;r++){  // 1  // Read data form xl file storing them in 1 dimension array
                apiData[r-1] = xlUtil.getCellData("Sheet1",r,1);
        }
        return apiData;
    }
}
