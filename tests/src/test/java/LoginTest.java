import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;

public class LoginTest {
    private WebDriver driver;

    @Before
    public void setup()  throws MalformedURLException  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--charset=UTF-8");
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() throws Exception {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(ConfigReader.getProperty("email"), ConfigReader.getProperty("pwd"));
        Assert.assertTrue(loginPage.isLoggedIn());
    }

    @Test
    public void testSearchWithUser() throws Exception {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(ConfigReader.getProperty("email"), ConfigReader.getProperty("pwd"));
        Assert.assertTrue(loginPage.isLoggedIn());

        SearchResultPage searchResultPage = loginPage.searchWithUser(ConfigReader.getProperty("searchProduct"));
        Assert.assertTrue(searchResultPage.isResultsShowing());
    }

    @Test
    public void testLogout() throws Exception {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(ConfigReader.getProperty("email"), ConfigReader.getProperty("pwd"));
        Assert.assertTrue(loginPage.isLoggedIn());
        loginPage.logout();
        Assert.assertTrue(!loginPage.isLoggedIn());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}