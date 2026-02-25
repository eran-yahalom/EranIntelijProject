package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Starting test" + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test was successful " + result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Stop running Suite " + context.getName());
    }
}
