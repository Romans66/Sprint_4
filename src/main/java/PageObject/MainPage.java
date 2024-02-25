package PageObject;

import PageBaseSteps.PageSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainPage extends PageSettings {
    private final String URL_MAIN_PAGE = "https://qa-scooter.praktikum-services.ru/";
    
    public MainPage(WebDriver driver) {
        super(driver);
    }
    
    // Переменные с ожидаемым результатом
    public static final String HOW_IT_WORKS_HEADER_TEXT_EXPECTED = "Вопросы о важном";
    
    // Заголовок блока "Как это работает"
    private By howItWorksHeader = By.xpath("//div[text()  = 'Вопросы о важном']");
    
    // Список элементов с вопросами
    private By questionsOfFAQ = By.xpath("//div[@class='accordion']/div/div/div");
    
    // Список элементов с ответами
    private By answersOfFAQ = By.xpath("//div[@role='region']/p");
    
    // Кнопка "Заказать" в хедере страницы
    private By orderHeaderButton = By.xpath("//button[@class='Button_Button__ra12g']");
    
    // Кнопка "Заказать" в середине страницы
    private By orderMiddleButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    
    // Лого в хедере
    private By yandexLogo = By.xpath("//a[@class='Header_LogoYandex__3TSOI']");
    private By samokatLogo = By.xpath("//a[@class='Header_LogoScooter__3lsAR']");
    
    // Атрибуты для поиска заказа по статусу
    private By firstOrderStatusButton = By.xpath("//button[text()='Статус заказа']");
    private By orderStatusInput = By.xpath("//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");
    private By finalOrderStatusButton = By.xpath("//button[text()='Go!']");
    
    public MainPage openMainPage() {
        driver.get(URL_MAIN_PAGE);
        return this;
    }
    
    public String getMainPageURL() {
        return URL_MAIN_PAGE;
    }
    
    // Извлекаем текст из ответов и вопросов (помещаем в мап) и проверяем, что они отображаются
    public Map<String, String> actualQuestionsAndAnswersTextCollection() {
        Map<String, String> actualQuestionsAndAnswersMap = new LinkedHashMap<>();
        List<WebElement> questionsOfFAQElements = driver.findElements(questionsOfFAQ);
        List<WebElement> answersOfFAQElements = driver.findElements(answersOfFAQ);
        for (int i = 0; i < questionsOfFAQElements.size(); i++) {
            questionsOfFAQElements.get(i).isDisplayed();
            
            //Кликаем на элемент (через click не работает, реализовал через JS-executor)
            javaScriptClickToElement(questionsOfFAQElements.get(i));
            answersOfFAQElements.get(i).isDisplayed();
            actualQuestionsAndAnswersMap.put(questionsOfFAQElements.get(i).getText(), answersOfFAQElements.get(i).getText());
        }
        return actualQuestionsAndAnswersMap;
    }
    
    // Извлекаем текст из заголовка в блоке FAQ
    public String getTextOfFAQHeader() {
        return driver.findElement(howItWorksHeader).getText();
    }
    
    // Методы для нажатия на кнопки "Заказать"
    public OrderPage orderHeaderButtonClick() {
        driver.findElement(orderHeaderButton).click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
    
    public OrderPage orderMiddleButtonClick() {
        WebElement orderMiddleButtonElement = driver.findElement(orderMiddleButton);
        javaScriptScrollToElement(orderMiddleButtonElement);
        orderMiddleButtonElement.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
    
    // Методы для клика на лого в хедере
    public MainPage yandexLogoClick() {
        driver.findElement(yandexLogo).click();
        return this;
    }
    
    public MainPage samokatLogoClick() {
        driver.findElement(samokatLogo).click();
        return this;
    }
    
    // Методы для работы с атрибутами для поиска статуса заказа
    public MainPage firstOrderStatusButtonClick() {
        driver.findElement(firstOrderStatusButton).click();
        return this;
    }
    
    public MainPage EnteringOrderStatus(String orderNumber) {
        driver.findElement(orderStatusInput).sendKeys(orderNumber);
        return this;
    }
    
    public MainPage finalOrderStatusButtonClick() {
        driver.findElement(finalOrderStatusButton).click();
        return this;
    }
    
    public StatusOrderPage orderStatusSearch(String orderNumber) {
        StatusOrderPage StatusOrderPage = new StatusOrderPage(driver);
        firstOrderStatusButtonClick();
        EnteringOrderStatus(orderNumber);
        finalOrderStatusButtonClick();
        return StatusOrderPage;
    }
}