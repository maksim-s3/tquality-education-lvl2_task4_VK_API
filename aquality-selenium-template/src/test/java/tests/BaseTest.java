package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.rest_assured.RestClient;
import aquality.selenium.template.utilities.Listener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static aquality.selenium.browser.AqualityServices.getBrowser;

@Listeners(Listener.class)
public abstract class BaseTest {
    protected final IElementFactory elementFactory;

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
    }

    @Parameters({"token"})
    @BeforeMethod
    protected void beforeMethod(String token) {
        RestClient.setToken(token);
        RestClient.setVersionApi(Configuration.getVersionApi());
        getBrowser().maximize();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
