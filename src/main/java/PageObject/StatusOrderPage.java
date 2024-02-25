package PageObject;

import PageBaseSteps.PageSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatusOrderPage extends PageSettings {
    private final String URL_ORDER_PAGE = "https://qa-scooter.praktikum-services.ru/track?t=";
    
    public StatusOrderPage(WebDriver driver) {
        super(driver);
    }
    
    private By notFoundOrderImg = By.xpath("//img[@alt='Not found']");
    
    public boolean notFoundOrderImgCheck() {
        waitOfElement(notFoundOrderImg);
        return driver.findElement(notFoundOrderImg).isDisplayed();
    }
    
}
