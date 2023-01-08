package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File("target\\ExtentReports\\extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setReportName("Task02 User Interface Test Report");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        Properties configProp = new Properties();
        File configPropFile = new File("src/test/resources/config/config.properties");
        try {
            FileInputStream fisConfigProp = new FileInputStream(configPropFile);
            configProp.load(fisConfigProp);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));

        return extentReport;
    }
}
