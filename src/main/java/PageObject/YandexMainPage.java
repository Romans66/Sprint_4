package PageObject;

import PageBaseSteps.PageSettings;
import org.openqa.selenium.WebDriver;

public class YandexMainPage extends PageSettings {
    private final String URL_MAIN_YANDEX_PAGE = "https://dzen.ru/?yredirect=true";
    
    public YandexMainPage(WebDriver driver) {
        super(driver);
    }
    
    public String getYandexURL() {
        return URL_MAIN_YANDEX_PAGE;
    }
}
