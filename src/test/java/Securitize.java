import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@TestMethodOrder(OrderAnnotation.class)
public class Securitize {

    private WebDriver driver;

    @Before
    public void setUp(){

        System.setProperty("webdriver.chrome.driver", "./src/main/resources/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.practis.co.il/automation");
    }

    public void login(){
        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.name("login"));

        username.sendKeys("admin");
        password.sendKeys("admin");
        loginButton.click();
    }

    @Test
    public void testLogin(){
        login();
        String url = driver.getCurrentUrl();
        assertTrue(url.equals("https://www.practis.co.il/automation/main.php"));

    }

    @Test
    public void buttonDo1(){
        login();
        WebElement buttonDo1 = driver.findElement(By.id("do1"));
        buttonDo1.click();
        assertFalse((buttonDo1.isEnabled()));
    }

    @Test
    public void buttonDo2(){
        login();
        WebElement buttonDo2 = driver.findElement(By.id("do2"));
        buttonDo2.click();
        assertFalse((buttonDo2.isEnabled()));
    }

    @Test
    public void fontSizeIncrease(){
        login();
        WebElement btnIncreaseFont = driver.findElement(By.id("btnIncreaseFont"));
        WebElement textFontSize =  driver.findElement(By.id("textFontSize"));
        int original = Integer.valueOf(textFontSize.getCssValue("font-size").replace("px", ""));
        btnIncreaseFont.click();
        int after = Integer.valueOf(textFontSize.getCssValue("font-size").replace("px", ""));
        assertTrue(after > original);
    }

    @Test
    public void fontSizeDecrease(){
        login();
        WebElement btnDecreaseFont = driver.findElement(By.id("btnDecreaseFont"));
        WebElement textFontSize =  driver.findElement(By.id("textFontSize"));
        int original = Integer.valueOf(textFontSize.getCssValue("font-size").replace("px", ""));
        btnDecreaseFont.click();
        int after = Integer.valueOf(textFontSize.getCssValue("font-size").replace("px", ""));
        assertTrue(after < original);
    }

    @Test
    public void backgroundColor(){
        login();
        WebElement btnSetBgColor = driver.findElement(By.id("btnSetBgColor"));
        WebElement bgColor = driver.findElement(By.id("bgColor"));
        WebElement formToColorize = driver.findElement(By.id("formToColorize"));
        String originalColor = formToColorize.getCssValue("background-color");
        bgColor.sendKeys("#ff0000");
        btnSetBgColor.click();
        String afterColor = formToColorize.getCssValue("background-color");
        assertFalse(originalColor.equals(afterColor));
    }



    @After
    public void tearDown() {
        driver.quit();
    }



}
