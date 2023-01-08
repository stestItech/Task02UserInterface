package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Utilities {

    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_WAIT_TIME = 5;

    public static void uploadFile(String fileName) {
        try {
            Robot rb = new Robot();
            String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
                    + "test" + File.separator + "resources" + File.separator + "upload_file"
                    + File.separator + fileName;
            StringSelection str = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
            rb.delay(3000);

            // press Contol+V for pasting
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            // release Contol+V for pasting
            rb.keyRelease(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.delay(3000);
            // for pressing and releasing Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String captureScreenshot(WebDriver driver, String testName) {
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //String destinationScreenshotPath = "..\\screenshots\\" + testName + ".png";
        String destinationScreenshotPath = ".\\target\\screenshots\\" + testName + ".png";
        //String destinationScreenshotPath = System.getProperty("user.dir") + "\\target\\screenshots\\"
                //+ testName + ".png";
        try {
            FileUtils.copyFile(screenShot, new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationScreenshotPath;
    }
}
