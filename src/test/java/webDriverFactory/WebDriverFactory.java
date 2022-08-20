package webDriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.Locale;

public class WebDriverFactory {
    public static String browser;
    public static WebDriver getDriver(){

        browser = System.getProperty("browser");

        if (browser == null) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }

        switch (browser.toLowerCase(Locale.ROOT).trim()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "opera":
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

        }
        return new ChromeDriver();
    }
    public static WebDriver getDriverWithOption(String arg){

        browser = System.getProperty("browser");

        if (browser == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments(arg);
            return new ChromeDriver(options);
        }

        switch (browser.toLowerCase(Locale.ROOT).trim()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments(arg);
                return new ChromeDriver(options);
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options1 = new FirefoxOptions();
                options1.addArguments(arg);
                return new FirefoxDriver(options1);
            case "opera":
                WebDriverManager.operadriver().setup();
                OperaOptions options2 = new OperaOptions();
                options2.addArguments(arg);
                return new OperaDriver(options2);
            case "edge":
                WebDriverManager.edgedriver().setup();
                WebDriverManager.edgedriver().setup();
                EdgeOptions options3 = new EdgeOptions();
                options3.addArguments(arg);
                return new EdgeDriver(options3);

        }
        return new ChromeDriver();
    }
}
