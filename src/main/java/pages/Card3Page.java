package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Card3Page {

    WebDriver driver;

    @FindBy(css = ".page-indicator")
    private WebElement pageIndicator;

    public Card3Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCardNumber() {
        int cardNumber = Integer.parseInt(pageIndicator.getText().split(" ")[0]);
        return cardNumber;
    }
}
