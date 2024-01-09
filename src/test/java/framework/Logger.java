package framework;

import org.testng.Reporter;

public class Logger {

    public static void logInfo(String message) {
        Reporter.log("[INFO] " + message);
    }

    public static void logStep(int stepNumber) {
        Reporter.log("<br/><b>Step:</b> " + stepNumber + "<br/>");
    }

    public static void logSteps(int startStep, int finishStep) {
        Reporter.log("<br/><b>Step " + startStep + "/" + finishStep + ":</b><br/>");
    }
}
