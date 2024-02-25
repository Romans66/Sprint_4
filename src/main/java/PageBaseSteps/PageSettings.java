package PageBaseSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

public class PageSettings {
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    protected Random randomNumber = new Random();
    
    public PageSettings(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
    
    public void javaScriptScrollToElement(WebElement element) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }
    
    public void javaScriptClickToElement(WebElement element) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    
    public void waitOfElement(By element) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    
    public void browserSecondTabSwitcher() {
        String mainWindowHandle = driver.getWindowHandle();
        
        // Получаем все идентификаторы открытых вкладок
        Set<String> allWindowHandles = driver.getWindowHandles();
        // Проходимся по каждой вкладке
        for (String windowHandle : allWindowHandles) {
            // Если это не текущая вкладка, переключаемся на нее
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
