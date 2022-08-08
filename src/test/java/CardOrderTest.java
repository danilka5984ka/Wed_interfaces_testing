
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CardOrderTest {

    private WebDriver driver;

    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp1() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void close() {
        driver.quit();
        driver = null;
    }

    @Test
    void test() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Петров-Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79454534455");
        driver.findElement(By.cssSelector("[data-test-id='agreement'] input")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success'] input")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void test1() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Петров-Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79454534455");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String text = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
}
