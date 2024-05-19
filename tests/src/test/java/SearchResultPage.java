import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;

class SearchResultPage extends PageBase {

    private By resultTableOfProductsLocator = By.xpath("//div[@class='page_content artlist--type-1']");
    
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }    

    public boolean isResultsShowing() {
        return this.waitAndReturnElement(resultTableOfProductsLocator).isDisplayed();
    }
}
