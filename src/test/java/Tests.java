import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.xpath;

public class Tests extends WebDriverInit{

    @Test
    public void test1(){
        //Открыть Chrome в headless режиме
        chrome("--headless");
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
        By element = xpath( position + "//span[text()='" + expectedText + "']");
        //проверка результата
        assertTrue(checkElement(element));
    }

    @Test
    public void test2() throws InterruptedException {
        //Открыть Chrome...
        chrome();
        //Перейти на https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        //...в режиме киоска
        driver.manage().window().fullscreen();
        //Нажать на любую картинку(для выбора картинки "любой" подключаем рандомайзер)
        //Для начала находим все картинки
        List<WebElement> photos = driver.findElements(By.xpath("//li[@class='portfolio-item2 content']"));
        //Посчитаем их
        int quantityPhotos = photos.size();
        //Подключаем рандомайзер
        Random random = new Random();
        //задаем рандомайзер на значения от "0" до максимального количества картинок
        int n = random.nextInt(quantityPhotos);
        //картинки под номером "0" нет, потому 0 рандомайзера меняем на 1
        if(n==0)n=1;
        //БЕЗ ПРОКРУТКИ СТРАНИЦЫ, КАРТИНКИ НАХОДЯЩИЕСЯ ЗА ПРЕДЕЛОМ ЭКРАНА НЕ КЛИКАБЕЛЬНЫ!!!
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if(n>3){
            //в зависимости от "глубины" картинки определяем "глубину" прокрутки
            js.executeScript("scroll(0, 1000);");
            if(n>6)js.executeScript("scroll(0, 1300);");
        }
        //Без принудительной паузы может выдать ошибку о том что элемент не кликабелен
        Thread.sleep(1000);
        ////Нажать на любую картинку(выбранную выше рандомайзером)
        driver.findElement(xpath("//li[" + n + "][@class='portfolio-item2 content']//div[1]")).click();
        //Проверить что картинка открылась в модальном окне
        assertTrue(checkElement(xpath("//img[@id='fullResImage']")));
    }

    @Test
    public void test3() throws InterruptedException {
        //Открыть Chrome в режиме полного экрана
        chrome();
        driver.manage().window().maximize();
        //ерейти на https://otus.ru
        driver.get("https://otus.ru");
        //Авторизоваться под каким-нибудь тестовым пользователем
        driver.findElement(xpath("//button")).click();
       //driver.findElement(xpath("//input[@name='email']")).sendKeys("test10.jack@yandex.ru"); //выдает ошибку, что данный инпут не интерактивный объект
       // driver.findElement(xpath("//input[@type='password']")).sendKeys("Qwerty1234$"); //выдает ошибку, что данный инпут не интерактивный объект
        driver.findElement(xpath("//button[@type='submit']"));
        //Вывести в лог все cookie
        logger = LogManager.getLogger(WebDriverInit.class);
        logger.info(driver.manage().getCookies());
    }

    @AfterEach
    public void exit(){
        if(driver != null) driver.quit();
    }
}

