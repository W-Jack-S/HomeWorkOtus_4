import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Random;

import static io.opentelemetry.sdk.trace.IdGenerator.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.xpath;

public class Test_1 extends WebDriverInit{

    @Test
    public void test1(){
        //Открыть Chrome в headless режиме
        chrome("headless");
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
    public void test2(){
        chrome();
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
       // driver.manage().window().fullscreen();
        //driver.findElement(xpath("//*[@id='search_form_input_homepage']")).sendKeys("ОТУС");
        List<WebElement> photos = driver.findElements(By.xpath("//li[@class='portfolio-item2 content']"));
        int quantityPhotos = photos.size();
        Random random = new Random();
        int n = random.nextInt(quantityPhotos);
        Actions moving = new Actions(driver);
        moving.moveToElement(driver.findElement(xpath("//li[" + n + "][@class='portfolio-item2 content']/span/a/div[1]"))).build().perform();
       driver.findElement(xpath("//li[" + n + "][@class='portfolio-item2 content']/span/a/div[1]")).click();
       // String position = "//article[@id='r1-0']";
       // String expectedText = "Онлайн‑курсы для профессионалов, дистанционное обучение современным ...";
       // By element = xpath( position + "//span[text()='" + expectedText + "']");
       // assertTrue(checkElement(element)); v/html/body/section[2]/div/ul[2]/li[2]/span/a/div[1]

    }


    //@AfterEach
    public void exit(){
        if(driver != null) driver.quit();
    }
}

