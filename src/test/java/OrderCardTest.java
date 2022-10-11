import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class OrderCardTest {
    private WebDriver driver;


    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        driver = new ChromeDriver(options);
    }


    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void ShouldCardOrder() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Евгений Серков");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79999999999");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
}

