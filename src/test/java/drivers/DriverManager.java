package drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static AppiumDriver driver;

    public static void initializeDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

                // Ruta absoluta o relativa del APK
                String appPath = System.getProperty("user.dir") + "/src/test/resources/apks/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
                caps.setCapability(MobileCapabilityType.APP, appPath);

                // Nombre del paquete y actividad principal (solo si no usas 'app')
                caps.setCapability("appPackage", "com.swaglabsmobileapp");
                caps.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");

                driver = new AppiumDriver(new URL("http://127.0.0.1:4723/"), caps);
            } catch (MalformedURLException e) {
                logger.error("Error al inicializar AppiumDriver", e);
            }
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}