import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class Configuration {
    public static WebDriver driver;

    public static WebDriver createDriver()
    {
        System.setProperty(Constants.DRIVER_PROP_NAME, Constants.DRIVER_PROP_VALUE);

        if(Objects.isNull(driver))
        {
            driver = new ChromeDriver();
        }

        return driver;
    }

    public static WebDriver getDriver(String url)
    {
        createDriver().get(url);
        return driver;
    }
}
