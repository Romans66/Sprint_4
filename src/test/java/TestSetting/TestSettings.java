package TestSetting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class TestSettings {
    protected WebDriver driver;
    
    @Before
    public void startSetUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        
        driver.manage().window().maximize();
        
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    @After
    public void tearDown() throws InterruptedException {
        driver.quit();
    }
}
