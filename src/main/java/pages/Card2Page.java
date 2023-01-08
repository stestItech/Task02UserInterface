package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Card2Page {

    WebDriver driver;

    @FindBy(css = ".page-indicator")
    private WebElement pageIndicator;

    @FindBy(xpath = "//span[text()='Unselect all']/preceding-sibling::span/label")
    private WebElement unselectAllCheckBox;

    @FindBy(xpath = "//span[text()='Dough']/preceding-sibling::span/label")
    private WebElement doughCheckBox;

    @FindBy(xpath = "//span[text()='Snails']/preceding-sibling::span/label")
    private WebElement snailsCheckBox;

    @FindBy(xpath = "//span[text()='Purple']/preceding-sibling::span/label")
    private WebElement purpleCheckBox;

    @FindBy(xpath = "//a[text()='upload']")
    private WebElement uploadLink;

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    public Card2Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCardNumber() {
        int cardNumber = Integer.parseInt(pageIndicator.getText().split(" ")[0]);
        return cardNumber;
    }

    public void checkUnselectAllCheckBox() {
        unselectAllCheckBox.click();
    }

    public void checkDoughCheckBox() {
        doughCheckBox.click();
    }

    public void checkSnailsCheckBox() {
        snailsCheckBox.click();
    }

    public void checkPurpleCheckBox() {
        purpleCheckBox.click();
    }

    public void clickUploadLink() {
        uploadLink.click();
    }

    public Card3Page clickNextButton() {
        nextButton.click();
        return new Card3Page(driver);
    }
}
