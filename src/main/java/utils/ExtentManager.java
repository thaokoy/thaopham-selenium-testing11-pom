package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

//    handle thêm lưu log parallel khi chạy song song nhiều test case
    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    public static void setCurrentTest(ExtentTest test) {
        currentTest.set(test);
    }

//    Mỗi thread sẽ có Extent test riêng
//    chức năng hàm: lấy instance thread hiện tại để ghi log vào
    public static ExtentTest getCurrentTest() {
        return currentTest.get();
    }

    public static void removeCurrentTest() {
        currentTest.remove();
    }

    public static synchronized ExtentReports getExtentReports() {
//        co che singleton
//        kiem tra extent ton tai chua
//        neu ton tai => return
//        Ý NGHĨA:
//        - tránh tạo nhiều extent -> tránh mất log test case
//        - tập trung các log test case vào 1 file html
        if (extent != null) {
            return extent;
        }

//        define đường dẫn folder để lưu report => test-output
//        user.dir => lấy đường dẫn hiện tại của project trong local
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

//        ExtentSparkReporter chịu trách nhiệm tạo file html
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
//        config: chỉnh theme, define title
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation test report");
        spark.config().setReportName("Selenium POM - TestNG");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        return extent;
    }
}
