package headerLogoTest;

import PageObject.MainPage;
import PageObject.YandexMainPage;
import TestSetting.TestSettings;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class headerLogoTest extends TestSettings {
    private MainPage mainPage;
    private YandexMainPage yandexMainPage;
    
    @Before
    public void startSetUpTest() {
        mainPage = new MainPage(driver);
        yandexMainPage = new YandexMainPage(driver);
    }
    
    @Test
    public void headerYandexLogoCheck() {
        
        mainPage
                .openMainPage()
                .yandexLogoClick()
                .browserSecondTabSwitcher();
        
        assertThat(yandexMainPage.getCurrentURL(), is(yandexMainPage.getYandexURL()));
        
    }
    
    @Test
    public void headerSamokatLogoCheck() {
        mainPage
                .openMainPage()
                .samokatLogoClick();
        
        assertThat(mainPage.getCurrentURL(), is(mainPage.getMainPageURL()));
    }
    
}
