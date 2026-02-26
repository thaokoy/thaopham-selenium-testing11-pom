package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

// Cho phép "bắt event" vòng đời test: start, pass/fail/skip, after test
public class ExtentTestListener implements ITestListener {
    public ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result){
        String testName = result.getMethod().getMethodName();
        ExtentReports extentReports = ExtentManager.getExtentReports();
        extentTest = extentReports.createTest(testName);

//        Lưu info log test vào ThreadLocal
        ExtentManager.setCurrentTest(extentTest);

        extentTest.log(Status.INFO, "Start testing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest currentTest = ExtentManager.getCurrentTest();
        System.out.println("info currentTest: " + currentTest);
        if(currentTest != null) {
            extentTest.log(Status.PASS, "Test pass");
        }
//        remove thread log test
        ExtentManager.removeCurrentTest();

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReports extentReports = new ExtentReports();
        if(extentReports != null) {
            extentReports.flush();
        }
    }
}
