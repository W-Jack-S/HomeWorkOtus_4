import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverInit {
    public WebDriver driver;


    //ИНИЦИАЛИЗАЦИЯ БРАУЗЕРА

    //СТАРТОВЫЕ ПАРАМЕТРЫ БРАУЗЕРА
    public void startSetting(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    //ЗАПУСК GOOGLE CHROME
    public void chrome(String arg){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(arg);
        driver = new ChromeDriver();
    }
    public void chrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    //ЗАПУСК MOZILLA FIREFOX
    public void fireFox(String arg){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(arg);
        driver = new FirefoxDriver();
    }

    public void fireFox(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    //ЗАПУСК БРАУЗЕРА OPERA
    public void opera(String arg){
        WebDriverManager.operadriver().setup();
        OperaOptions options = new OperaOptions();
        options.addArguments(arg);
        driver = new OperaDriver();
    }

    public void opera(){
        WebDriverManager.operadriver().setup();
        driver = new OperaDriver();
    }

    //ЗАПУСК БРАУЗЕРА EDGE
    public void edge(String arg){
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments(arg);
        driver = new EdgeDriver();
    }

    public void edge(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
    }

    public boolean checkElement(By element){
        try{
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {return false;}
    }


}
