package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import base.TestBase;
import io.qameta.allure.Step;

public class AmazonPage extends TestBase{
  
WebDriver driver;

  @FindBy(css = "#nav-hamburger-menu")
  private WebElement hamburgerMenu;
  @FindBy(xpath = "//div[text()='TV, Appliances, Electronics']//parent::a")
  private WebElement elemShopByCategory;
  @FindBy (xpath = "//a[text()='Televisions']")
  private WebElement elemTelevision;  
  @FindBy(xpath = "//span[text()='Brands']/../../ul/li")
  private List<WebElement> listBrandItems;
  @FindBy(xpath = "//span[text()='Brands']")
  private WebElement txtBrand;
  @FindBy(xpath  = "//label[text()='Sort by:']//following::select")
  private WebElement elemSortBy;
  @FindBy(css = "span[aria-label='Sort by:']")
  private WebElement elemSortByClickable;
  @FindBy(css = "div[cel_widget_id='MAIN-SEARCH_RESULTS-2']>div>div>div>div>h2>a")
  private WebElement elemSecondItem;
  @FindBy(css = "#feature-bullets>ul")
  private WebElement txtAboutItem;
  @FindBy(css = "#feature-bullets>h1")
  private WebElement txtAboutItemHeader;

  /* enum SortType{
    HIGH_TO_LOW("Price: High to Low");
    private String sortValue;
    public String getSortValue(){
      return sortValue;
    }
    private SortType(String sortValue){
      this.sortValue = sortValue;
    }
  } */

  private static final String sortValue = "Price: High to Low";

  public AmazonPage(WebDriver driver){
    this.driver = driver;
    //wait = new WebDriverWait(driver,5,5 );
    PageFactory.initElements(driver, this);
  }

  @Step("Hamburger Menu Item Functionality")
   public void clickHamBurgerMenuItem(){
    this.hamburgerMenu.click();
    waitForElement(elemShopByCategory);
   }
   @Step("Choose from Category")
   public void clickCategoryItem() {
    scrollElementIntoView(elemShopByCategory);
    this.elemShopByCategory.click();
    waitForElement(elemTelevision);
   }
   @Step("Choose Television Menu")
   public void clickTelevisionMenu(){
    this.elemTelevision.click();
    waitForElement(txtBrand);
   }

   @Step("Filter with specific brand")
   public void filterSpecificBrand(String filterName){ 
    scrollElementIntoView(this.txtBrand);
     int listSize = this.listBrandItems.size();
     WebElement brandItem = null;
     String brandValue = null;
     for(int i=1;i<=listSize;i++){
         brandItem = driver.findElement(By.xpath("//span[text()='Brands']/../../ul/li["+i+"]/span/a/span"));
         brandValue = brandItem.getText();
         if(brandValue.equals(filterName)){
             driver.findElement(By.xpath("//span[text()='Brands']/../../ul/li["+i+"]/span/a")).click();
             driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
             break;
         }
         else{
             continue;
         }
     }
   }

   @Step("Sort by High to Low")
   public void sortBy(){
    try{
      selectDropDownValue(sortValue, elemSortBy,elemSortByClickable);
    }catch(Exception e){
      LOGGER.info(e.toString());
    }
   }
   @Step("Click on the second highest rate / lowest rate item - Highest Rate")
   public void clickSecondItem(){
    this.elemSecondItem.click();
   }
   @Step("Section of Item Details")
   public String getAboutThisItemValue(){
    String aboutThisItem = this.txtAboutItem.getText();
    return aboutThisItem;
   }
   @Step("Header of Item Details")
   public String getAboutThisHeader(){
    scrollElementIntoView(txtAboutItemHeader);
    String aboutThisItemHeader = this.txtAboutItemHeader.getText();
    return aboutThisItemHeader;
   }
}
