package PageObject;

import PageBaseSteps.PageSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage extends PageSettings {
    private final String URL_ORDER_PAGE = "https://qa-scooter.praktikum-services.ru/order";
    
    public OrderPage(WebDriver driver) {
        super(driver);
    }
    
    // Переменные с ожидаемым результатом
    public static final String HEADER_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED = "Заказ оформлен";
    public static final String DATA_OF_ORDER_FINAL_MODEL_WINDOW_TEXT_EXPECTED = "Номер заказа";
    
    // Локаторы полей 1 формы заполнения для оформления заказа
    private By inputOfName = By.xpath("//input[@placeholder='* Имя']");
    private By inputOfSurname = By.xpath("//input[@placeholder='* Фамилия']");
    private By inputOfAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By dropDownOfMetroStation = By.xpath("//input[@placeholder='* Станция метро']");
    private By inputOfTelephoneNumber = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By metroStationList = By.xpath("//li[@class = 'select-search__row']/button/div[text()]");
    private By continueOrderButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    
    // Локаторы полей 2 формы заполнения для оформления заказа
    private By inputOfDatePicker = By.xpath("//input[@placeholder = '* Когда привезти самокат']");
    private By datePickerWindow = By.xpath("//div[@class = 'react-datepicker__month-container']\n");
    private By dateInDataPicker = By.xpath("//div[@class = 'react-datepicker__week']/div");
    private By rentalPeriod = By.xpath("//div[@class = 'Dropdown-placeholder']");
    private By rentalPeriodWindow = By.xpath("//div[@class = 'Dropdown-menu']");
    private By rentalPeriodDropDown = By.xpath("//div[@class = 'Dropdown-menu']/div[@class='Dropdown-option']");
    private By colorsOfScooterCheckBox = By.xpath("//div[@class = 'Order_Checkboxes__3lWSI']/label/input");
    private By inputOfCourierComment = By.xpath("//input[@placeholder= 'Комментарий для курьера']");
    private By secondOrderButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    
    // Модальное окно "Подтверждение заказа"
    private By finalOrderButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    private By modalOrderApproveWindow = By.xpath("//div[@class='Order_Modal__YZ-d3']");
    
    // Модальное окно "Заказ оформлен"
    private By modalOrderFinishedWindow = By.xpath("//div[@class='Order_Modal__YZ-d3']");
    
    private By nameOfModalOrderFinishedWindow = By.xpath("//div[text() = 'Заказ оформлен']");
    
    private By dataOfOrderFinishedWindow = By.xpath("//div[@class='Order_Text__2broi']");
    private By statusButtonOfOrderFinishedWindow = By.xpath("//button[text()='Посмотреть статус']");
    
    // Методы для заполнения первой формы для офорлмения заказа
    public OrderPage EnteringName(String name) {
        driver.findElement(inputOfName).sendKeys(name);
        return this;
    }
    
    public OrderPage EnteringSurname(String surName) {
        driver.findElement(inputOfSurname).sendKeys(surName);
        return this;
    }
    
    public OrderPage inputOfAddress(String address) {
        driver.findElement(inputOfAddress).sendKeys(address);
        return this;
    }
    
    public OrderPage inputOfTelephoneNumber(String phoneNumber) {
        driver.findElement(inputOfTelephoneNumber).sendKeys(phoneNumber);
        return this;
    }
    
    
    public OrderPage continueButtonClick() {
        driver.findElement(continueOrderButton).click();
        return this;
    }
    
    public OrderPage chooseRandomMetroStation() {
        // Выбираем рандомную станцию метро
        driver.findElement(dropDownOfMetroStation).click();
        List<WebElement> metroStationElementsList = driver.findElements(metroStationList);
        String nameOfMetro = metroStationElementsList.get(randomNumber.nextInt(metroStationElementsList.size())).getText();
        driver.findElement(dropDownOfMetroStation).sendKeys(nameOfMetro);
        List<WebElement> metroStationElementsList2 = driver.findElements(metroStationList);
        metroStationElementsList2.get(randomNumber.nextInt(metroStationElementsList2.size())).click();
        return this;
    }
    
    // Методы для заполнения второй формы оформления заказа
    public OrderPage chooseRandomDatePicker() {
        driver.findElement(inputOfDatePicker).click();
        driver.findElement(datePickerWindow).isDisplayed();
        List<WebElement> dateInDataPickerList = driver.findElements(dateInDataPicker);
        dateInDataPickerList.get(
                        randomNumber
                                .nextInt(dateInDataPickerList
                                        .size()))
                .click();
        return this;
    }
    
    public OrderPage rentalPeriodClick() {
        driver.findElement(rentalPeriod).click();
        driver.findElement(rentalPeriodWindow).isDisplayed();
        List<WebElement> rentalPeriodList = driver.findElements(rentalPeriodDropDown);
        rentalPeriodList.get(
                        randomNumber
                                .nextInt(rentalPeriodList
                                        .size()))
                .click();
        return this;
    }
    
    public OrderPage chooseColorsOfScooterCheckBox() {
        List<WebElement> colorsOfScooterCheckBoxList = driver.findElements(colorsOfScooterCheckBox);
        jsExecutor = (JavascriptExecutor) driver;
        colorsOfScooterCheckBoxList.get(
                        randomNumber
                                .nextInt(colorsOfScooterCheckBoxList
                                        .size()))
                .click();
        return this;
    }
    
    public OrderPage inputOfCourierComment(String comment) {
        driver.findElement(inputOfCourierComment).sendKeys(comment);
        return this;
    }
    
    public OrderPage secondOrderButtonClick() {
        driver.findElement(secondOrderButton).click();
        return this;
    }
    
    public OrderPage finalOrderButtonClick() {
        driver.findElement(modalOrderApproveWindow).isDisplayed();
        driver.findElement(finalOrderButton).click();
        return this;
    }
    
    // Метод для заполнения 1 формы оформления заказа
    public OrderPage fillTheFirstOrderForm(String name, String surName, String address, String phoneNumber) {
        // Заполняем поля в форме
        EnteringName(name);
        EnteringSurname(surName);
        inputOfAddress(address);
        inputOfTelephoneNumber(phoneNumber);
        chooseRandomMetroStation();
        return this;
    }
    
    // Метод для заполнения 2 формы оформления заказа
    public OrderPage fillTheSecondOrderForm(String comment) {
        // Заполняем поля в форме
        chooseRandomDatePicker();
        rentalPeriodClick();
        chooseColorsOfScooterCheckBox();
        inputOfCourierComment(comment);
        return this;
    }
    
    // Методы для работы с окном о завершении заказа
    
    public String getTextOfHeaderOrderModal() {
        return driver.findElement(nameOfModalOrderFinishedWindow).getText();
    }
    
    public String getTextOfDataOrderModal() {
        return driver.findElement(dataOfOrderFinishedWindow).getText();
    }
    
    public OrderPage finalOrderModalWindowCheck() {
        driver.findElement(modalOrderFinishedWindow).isDisplayed();
        driver.findElement(statusButtonOfOrderFinishedWindow).isDisplayed();
        driver.findElement(dataOfOrderFinishedWindow).isDisplayed();
        driver.findElement(nameOfModalOrderFinishedWindow).isDisplayed();
        
        return this;
    }
}
