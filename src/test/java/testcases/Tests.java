package testcases;

import base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Card1Page;
import pages.Card2Page;
import pages.Card3Page;
import pages.WelcomePage;
import utils.Utilities;

public class Tests extends Base {

    public static WebDriver driver;
    WelcomePage welcomePage;
    Card1Page card1;
    Card2Page card2;
    Card3Page card3;

    public Tests() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
        welcomePage = new WelcomePage(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void testCase1() {

        String actualWelcomeText = welcomePage.getWelcomeText1Paragraph().getText();
        Assert.assertTrue(actualWelcomeText.contains("Hi and welcome to User Inyerface,"));

        card1 = welcomePage.clickOnNextPageLink();
        Assert.assertEquals(card1.getCardNumber(), 1);
        card1.typePassword(dataProp.getProperty("password"));
        card1.typeEmail(dataProp.getProperty("email"));
        card1.typeDomain(dataProp.getProperty("domain"));
        card1.clickDomainDropDown();
        card1.selectNetValue();
        card1.checkTermsCheckBox();

        card2 = card1.clickNextLink();
        Assert.assertEquals(card2.getCardNumber(), 2);
        card2.checkUnselectAllCheckBox();
        card2.checkDoughCheckBox();
        card2.checkSnailsCheckBox();
        card2.checkPurpleCheckBox();
        card2.clickUploadLink();
        Utilities.uploadFile("foto.png");

        card3 = card2.clickNextButton();
        Assert.assertEquals(card3.getCardNumber(), 3);
    }

    @Test(priority = 2)
    public void testCase2() {
        String actualWelcomeText = welcomePage.getWelcomeText1Paragraph().getText();
        Assert.assertTrue(actualWelcomeText.contains("Hi and welcome to User Inyerface,"));

        card1 = welcomePage.clickOnNextPageLink();
        card1.clickSendToBottomBtn();
        Assert.assertFalse(card1.isHelpFormDisplayed());
    }

    @Test(priority = 3)
    public void testCase3() {
        String actualWelcomeText = welcomePage.getWelcomeText1Paragraph().getText();
        Assert.assertTrue(actualWelcomeText.contains("Hi and welcome to User Inyerface,"));

        card1 = welcomePage.clickOnNextPageLink();
        card1.closeCookiesPopup();
        Assert.assertFalse(card1.isCookiesPopupDisplayed());
    }

    @Test(priority = 4)
    public void testCase4() {
        String actualWelcomeText = welcomePage.getWelcomeText1Paragraph().getText();
        Assert.assertTrue(actualWelcomeText.contains("Hi and welcome to User Inyerface,"));

        card1 = welcomePage.clickOnNextPageLink();
        Assert.assertEquals(card1.getTimerValue(), "00:00:00");
    }
}
