package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {

    @DataProvider(name="csvData")
    public static Object[][] getDataFromCSV() {
        String csvPath = System.getProperty("user.dir") + "/src/test/java/resources/logindata/login-data.csv";
//          excel: Admin    admin123    success
//        =>code: List<Object[]>
        List<Object[]> data = new ArrayList<>();
//        1 số lưu ý khi đọc file
//        1: try-catch để handle lỗi đọc file nếu xảy ra
        try (FileReader fileReader = new FileReader(csvPath);
             CSVReader csvReader = new CSVReader(fileReader)) {

//            username,password,expectedResult
//            Admin,admin123,success
//            user1,pass1,fail

//            csv code:
//            [
//                ["username", "password", "expectedResult"],
//                ["Admin", "admin123", "success"],
//                ["user1", "pass1", "fail"]
//            ]

//            luu vao data de co the dung test
//            ["Admin", "admin123", "success"]
//            => Object[]: {"Admin", "admin123", "success"}
            List<String[]> allRows = csvReader.readAll();

            for(int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                data.add(row);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
//        Object[][]
        return data.toArray(new Object[0][]);
    }
}
