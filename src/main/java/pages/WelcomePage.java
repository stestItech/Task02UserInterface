package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BasePage {

    WebDriver driver;

    @FindBy(xpath = "//p[@class='start__paragraph'][1]")
    private WebElement welcomeText1Paragraph;

    @FindBy(xpath = "//a[text()='HERE']")
    private WebElement nextPageLink;

    public WelcomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getWelcomeText1Paragraph() {
        return welcomeText1Paragraph;
    }

    public Card1Page clickOnNextPageLink() {
        nextPageLink.click();
        return new Card1Page(driver);
    }
}
