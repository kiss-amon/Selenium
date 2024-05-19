import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;


class MainPage extends PageBase {

    private By searchBarFieldLocator = By.xpath("//input[@class='text_small ac_input js-search-input']");
    private By footerPartnersLocator = By.xpath("//div[@class='partners']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://pindurpalota.hu/");
    }

    public boolean isSearchBarLoaded() {
        return this.waitAndReturnElement(searchBarFieldLocator).isDisplayed();
    }

    public boolean isFooterPartnersVisible() {
        return this.waitAndReturnElement(footerPartnersLocator).isDisplayed();
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public SearchResultPage search(String searchQuery) {
        this.waitAndClick(searchBarFieldLocator);
        this.waitAndType(searchBarFieldLocator, searchQuery + "\n");
        return new SearchResultPage(this.driver);
        
    }
}
