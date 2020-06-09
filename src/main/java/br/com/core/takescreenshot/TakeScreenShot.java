package br.com.core.takescreenshot;


import br.com.utils.WebDriverHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot extends WebDriverHelper {

    public synchronized static byte[] getImageBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

}
