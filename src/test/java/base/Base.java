package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    public Properties prop;
    public Properties dataProp;

    public Base() {
        loadConfigPropertiesFile();
        loadTestDataPropertiesFile();
    }

    public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName) {

        if (browserName.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equals("Edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT_TIME));
        driver.get(prop.getProperty("url"));

        return driver;
    }

    public void loadConfigPropertiesFile() {
        prop = new Properties();
        File propFile = new File("src/test/resources/config/config.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void loadTestDataPropertiesFile() {
        dataProp = new Properties();
        File dataPropFile = new File("src/test/resources/testdata/testdata.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(dataPropFile);
            dataProp.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
