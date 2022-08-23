import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriverFactory.WebDriverFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.xpath;

public class TestsOtusHomework4 {
    public static WebDriver driver;
    public static Logger logger;


    @Test
    public void test1SearchOtus() {
        //Открыть Chrome в headless режиме
        //driver = WebDriverFactory.getDriverWithOption("--headless");
        driver = WebDriverFactory.getDriverWithOption();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Перейти на https://duckduckgo.com/
        driver.get("https://duckduckgo.com/");
        //В поисковую строку ввести "ОТУС"
        driver.findElement(xpath("//*[@id='search_form_input_homepage']")).sendKeys("ОТУС");
        driver.findElement(xpath("//*[@id='search_button_homepage']")).click();
        //Проверить что в поисковой выдаче первый результат Онлайн‑курсы для профессионалов, дистанционное обучение
        //XPath к первой позиции в поисковой выдаче
        String position = "//article[@id='r1-0']";
        //Текст, который необходимо найти
        String expectedText = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
        By element = xpath(position + "//span[text()='" + expectedText + "']");
        //проверка результата
        assertTrue(checkElement(element));
    }

    @Test
    public void test2OpenPhoto() throws InterruptedException {
        //Открыть Chrome...
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        //...в режиме киоска
        driver.manage().window().fullscreen();
        //Нажать на любую картинку(для выбора картинки "любой" подключаем рандомайзер)
        //Для начала находим все картинки
        List<WebElement> photos = driver.findElements(By.xpath("//a[@data-gal='prettyPhoto[gallery]']/.."));
        //Посчитаем их
        int quantityPhotos = photos.size();
        //Подключаем рандомайзер
        Random random = new Random();
        //задаем рандомайзер на значения от "0" до максимального количества картинок
        int n = random.nextInt(quantityPhotos);
        //картинки под номером "0" нет, потому 0 рандомайзера меняем на 1
        if (n == 0) n = 1;
        //БЕЗ ПРОКРУТКИ СТРАНИЦЫ, КАРТИНКИ НАХОДЯЩИЕСЯ ЗА ПРЕДЕЛОМ ЭКРАНА НЕ КЛИКАБЕЛЬНЫ!!!

           JavascriptExecutor js = (JavascriptExecutor) driver;
        if(n>3){
            //в зависимости от "глубины" картинки определяем "глубину" прокрутки
            js.executeScript("scroll(0, 1000);");
            if(n>6)js.executeScript("scroll(0, 1300);");
        }
        //Actions act = new Actions(driver);
        //act.scrollToElement(driver.findElement(xpath("//li[" + n + "]/span/a[@data-gal='prettyPhoto[gallery]']")));
        //Без принудительной паузы может выдать ошибку о том что элемент не кликабелен
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(xpath("//li[" + n + "]/span/a[@data-gal='prettyPhoto[gallery]']/div[1]")));
        //Нажать на любую картинку(выбранную выше рандомайзером)
        driver.findElement(xpath("//li[" + n + "]/span/a[@data-gal='prettyPhoto[gallery]']/div[1]")).click();
        //Проверить что картинка открылась в модальном окне
        assertTrue(checkElement(xpath("//img[@id='fullResImage']")));
    }

    @Test
    public void test3Authorization() {
        //Открыть Chrome в режиме полного экрана
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //ерейти на https://otus.ru
        driver.get("https://otus.ru");
        //Авторизоваться под каким-нибудь тестовым пользователем
        driver.findElement(xpath("//button")).click();
        WebElement form = driver.findElement(xpath("//form[@action='/login/']"));
        form.findElement(xpath(".//input[@name='email']")).sendKeys("test10.jack@yandex.ru");
        form.findElement(xpath(".//input[@name='password']")).sendKeys("Qwerty1234$");
        form.findElement(xpath(".//button[@type='submit']")).click();
        //Вывести в лог все cookie
        logger = LogManager.getLogger(WebDriverFactory.class);
        logger.info(driver.manage().getCookies());
    }

    @AfterEach
    public void exit() {
        if (driver != null) driver.quit();
    }


    public boolean checkElement(By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}