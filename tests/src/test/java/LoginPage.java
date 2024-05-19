import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

public class LoginPage extends PageBase {  

    private By acceptCookiesLocator = By.xpath("//button[@class='cookie-alert__btn-set btn btn--small btn--primary mt-3']");

    private By loginLocator = By.xpath("//button[@class='btn btn--primary btn--block']");
    private By emailFieldLocator = By.xpath("//input[@name='shop_user_login']");
    private By passwordFieldLocator = By.xpath("//input[@name='shop_pass_login']"); 

    private By searchBarFieldLocator = By.xpath("//input[@class='text_small ac_input js-search-input']");
    
    private By profileLocator = By.xpath("//button[@class='profile__btn header-btn js-profile-btn js-dropdown--btn dropdown--btn']");
    private By logoutLocator = By.xpath("//div[@id='tab_logout']");

    private By ordersLocator = By.xpath("//div[@id='tab_orders']");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://pindurpalota.hu");
    }

    public boolean isLoggedIn() {
        try {
            clickProfile();
            return this.waitAndReturnElement(ordersLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogin() throws Exception {
        this.waitAndClick(loginLocator);
    }

    public void enterEmail(String email) {
        this.waitAndType(emailFieldLocator, email);
    }

    public void enterPassword(String password) {
        this.waitAndType(passwordFieldLocator, password);
    }

    public void clickProfile() {
        this.waitAndClick(profileLocator);
    }

    public void clickLogout() {
        this.waitAndClick(logoutLocator);
    }

    public void acceptCookies() {
        this.waitAndClick(acceptCookiesLocator);
    }

    public void login(String email, String password) throws Exception {
        acceptCookies();
        clickProfile();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public void logout() {
        clickProfile();
        clickLogout();
    }

    public SearchResultPage searchWithUser(String searchQuery) {
        this.waitAndClick(searchBarFieldLocator);
        this.waitAndType(searchBarFieldLocator, searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }
}
