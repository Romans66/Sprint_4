package CreateOrderTest;

import PageObject.MainPage;
import PageObject.OrderPage;
import TestSetting.TestSettings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.OrderPage.DATA_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED;
import static PageObject.OrderPage.HEADER_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CreateOrderTest extends TestSettings {
    private final String NAME;
    private final String SURNAME;
    private final String ADDRESS;
    private final String PHONE_NUMBER;
    private final String COMMENT;
    private final String TEST_DESCRIPTION;
    private MainPage mainPage;
    private OrderPage orderPage;
    
    @Before
    public void startSetUpTest() {
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }
    
    public CreateOrderTest(String name,
                           String surName,
                           String address,
                           String phoneNumber,
                           String comment,
                           String testDescription) {
        NAME = name;
        SURNAME = surName;
        ADDRESS = address;
        PHONE_NUMBER = phoneNumber;
        COMMENT = comment;
        TEST_DESCRIPTION = testDescription;
    }
    
    @Parameterized.Parameters(name = "{5}")
    public static Object[][] getFormData() {
        return new Object[][] {
                {"Роман", "Фроленков", "Ленина д.28", "89646166563", "Тестовый коммент", "Кириллица - позитивный кейс"},
                {"Ivan", "Ivanov", "Lenina d.28", "+79646166563", "TestComment", "Латиница - негативный кейс"},
        };
        
    }
    
    @Test
    public void createOrderThroughTheHeaderButtonCheck() {
        
        // Оформляем заказ
        mainPage
                .openMainPage()
                .accessCookieButton()
                .orderHeaderButtonClick()
                .fillTheFirstOrderForm(NAME, SURNAME, ADDRESS, PHONE_NUMBER)
                .continueButtonClick()
                .fillTheSecondOrderForm(COMMENT)
                .secondOrderButtonClick()
                .finalOrderButtonClick()
                .finalOrderModalWindowCheck();
        
        // Проверяем текст из модального окна
        assertThat(orderPage.getTextOfHeaderOrderModal(), is(HEADER_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED));
    }
    
    @Test
    public void createOrderThroughTheMiddleButtonCheck() {
        // Оформляем заказ
        mainPage
                .openMainPage()
                .accessCookieButton()
                .orderMiddleButtonClick()
                .fillTheFirstOrderForm(NAME, SURNAME, ADDRESS, PHONE_NUMBER)
                .continueButtonClick()
                .fillTheSecondOrderForm(COMMENT)
                .secondOrderButtonClick()
                .finalOrderButtonClick()
                .finalOrderModalWindowCheck();
        
        // Проверяем текст из модального окна
        assertThat(orderPage.getTextOfHeaderOrderModal(), is(HEADER_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED));
        assertThat(orderPage.getTextOfDataOrderModal(), containsString(DATA_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED));
    }
}
