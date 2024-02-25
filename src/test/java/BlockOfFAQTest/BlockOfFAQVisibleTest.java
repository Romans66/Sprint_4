package BlockOfFAQTest;

import PageObject.MainPage;
import TestSetting.TestSettings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.MainPage.HOW_IT_WORKS_HEADER_TEXT_EXPECTED;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BlockOfFAQVisibleTest extends TestSettings {
    
    private final String EXPECTED_QUESTION;
    private final String EXPECTED_ANSWER;
    private MainPage mainPage;
    
    @Before
    public void startSetUpTest() {
        mainPage = new MainPage(driver);
    }
    
    public BlockOfFAQVisibleTest(String expectedQuestion, String expectedAnswer) {
        
        EXPECTED_QUESTION = expectedQuestion;
        EXPECTED_ANSWER = expectedAnswer;
        
    }
    
    @Parameterized.Parameters(name = "Вопрос номер: {0}")
    public static Object[][] getQuestionsAndAnswersData() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
            
        };
    }
    
    @Test
    public void checkQuestionsBlock() {
        
        mainPage.openMainPage();
        // Проверка заголовка из блока FAQ
        assertThat(mainPage.getTextOfFAQHeader(),
                is(HOW_IT_WORKS_HEADER_TEXT_EXPECTED));
        
        // Достаем по ключу ответ
        String actualAnswer =
                mainPage.actualQuestionsAndAnswersTextCollection()
                        .get(EXPECTED_QUESTION);
        
        // Сверяем полученный текст ответа с ожидаемым
        assertEquals(actualAnswer, EXPECTED_ANSWER);
    }
}



