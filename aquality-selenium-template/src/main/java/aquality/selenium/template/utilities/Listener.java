package aquality.selenium.template.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        AllureHelper.takeScreenshot();
        AllureHelper.takeLog();
    }

    public void onTestSuccess(ITestResult result) {
        AllureHelper.takeLog();
    }
}
