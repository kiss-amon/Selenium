import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.net.MalformedURLException;

public class MainPageTest {
    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testSearchBarIsLoaded() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.isSearchBarLoaded());
    }

    @Test
    public void testTitleValid() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getPageTitle().contains(ConfigReader.getProperty("pageTitle")));
    }

    @Test
    public void testFooterValid() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.isFooterPartnersVisible());
    }

    @Test
    public void testBodyValid() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getBodyText().contains("+36-30/826-7928"));
        Assert.assertTrue(mainPage.getBodyText().contains("pindurpalota@pindurpalota.hu"));
    }

    @Test
    public void testSearch() throws Exception {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.search(ConfigReader.getProperty("searchProduct"));
        Assert.assertTrue(searchResultPage.isResultsShowing());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
