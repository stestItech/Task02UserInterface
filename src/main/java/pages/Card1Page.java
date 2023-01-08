package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Card1Page {

    WebDriver driver;

    @FindBy(css = ".page-indicator")
    private WebElement pageIndicator;

    @FindBy(xpath = "//input[@placeholder='Choose Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@placeholder='Your email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@placeholder='Domain']")
    private WebElement domainInput;

    @FindBy(xpath = "//div[@class='dropdown__opener']")
    private WebElement domainDropDown;

    @FindBy(xpath = "//div[text()='.net']")
    private WebElement netValue;

    @FindBy(css = "span.icon.icon-check.checkbox__check")
    private WebElement termsCheckBox;

    @FindBy(xpath = "//*[text()='Next']")
    private WebElement nextLink;

    @FindBy(xpath = "//span[text()='to bottom']")
    private WebElement sendToBottomButton;

    @FindBy(xpath = "//h2[text()='How can we help?']")
    private WebElement helpFormTitle;

    @FindBy(xpath = "//button[text()='Not really, no']")
    private WebElement noButton;

    @FindBy(css = "div.cookies")
    private WebElement cookiesPopup;

    @FindBy(css = "div.timer.timer--white.timer--center")
    private WebElement timer;

    public Card1Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCardNumber() {
        int cardNumber = Integer.parseInt(pageIndicator.getText().split(" ")[0]);
        return cardNumber;
    }

    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void typeEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void typeDomain(String domain) {
        domainInput.clear();
        domainInput.sendKeys(domain);
    }

    public void clickDomainDropDown() {
        domainDropDown.click();
    }

    public void selectNetValue() {
        netValue.click();
    }

    public void checkTermsCheckBox() {
        termsCheckBox.click();
    }

    public Card2Page clickNextLink() {
        nextLink.click();
        return new Card2Page(driver);
    }

    public void clickSendToBottomBtn() {
        sendToBottomButton.click();
    }

    public void closeCookiesPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(noButton));
        noButton.click();
    }

    public boolean isHelpFormDisplayed() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.invisibilityOf(helpFormTitle));
        return helpFormTitle.isDisplayed();
    }

    public boolean isCookiesPopupDisplayed() {
        try {
            return cookiesPopup.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTimerValue() {
        return timer.getText();
    }
}
