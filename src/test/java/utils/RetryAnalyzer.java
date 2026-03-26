package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private final ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {
        int currentCount = count.get();

        if (currentCount < MAX_RETRY) {
            count.set(currentCount + 1);

            System.out.println("Retrying Scenario: "
                    + result.getMethod().getMethodName()
                    + " | Attempt: " + (currentCount + 1) + "/" + MAX_RETRY);

            return true;
        }
        return false;
    }
}