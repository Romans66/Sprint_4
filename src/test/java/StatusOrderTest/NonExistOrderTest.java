package StatusOrderTest;

import PageObject.MainPage;
import PageObject.StatusOrderPage;
import TestSetting.TestSettings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class NonExistOrderTest extends TestSettings {
    private MainPage mainPage;
    private StatusOrderPage statusOrderPage;
    private final String ORDER_STATUS_NUMBER;
    
    @Before
    public void startSetUpTest() {
        mainPage = new MainPage(driver);
        statusOrderPage = new StatusOrderPage(driver);
    }
    
    public NonExistOrderTest(String orderStatusNumber) {
        ORDER_STATUS_NUMBER = orderStatusNumber;
    }
    
    @Parameterized.Parameters(name = "Должна отображаться картинка")
    public static Object[][] getNumberOrderStatus() {
        return new Object[][] {
                {"999999999"},
                {"0"},
                {"-24242"},
                {" "},
                {"Тест"},
                {"Test"},
        };
    }
    
    @Test
    public void NonExistOrderTestCheck() {
        mainPage
                .openMainPage()
                .orderStatusSearch(ORDER_STATUS_NUMBER);
        assertTrue(statusOrderPage.notFoundOrderImgCheck());
    }
}
