package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;

public class ProgressListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("📊 התחלת הרצה - מכין דוחות התקדמות...");
    }
}
