package pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class AmazonPage {
  
WebDriver driver;
  @FindBy(css = "#nav-hamburger-menu")
  public WebElement hamburgerMenu;
  @FindBy(xpath = "//div[text()='TV, Appliances, Electronics']//parent::a")
  public WebElement elemShopByCategory;
  @FindBy (xpath = "//a[text()='Televisions']")
  public WebElement elemTelevision;  
  @FindBy(xpath = "//span[text()='Brands']/../../ul/li")
  public List<WebElement> listBrandItems;
  @FindBy(xpath = "//span[text()='Brands']")
  public WebElement txtBrand;
  @FindBy(css = "span.a-button-text")
  public WebElement elemSortBy;
  @FindBy(xpath = "//a[text()='Price: High to Low']")
  public WebElement elemHighToLow;
  @FindBy(css = "div.s-main-slot>div:nth-child(3)>div>div>div>div>div:nth-child(2)>div>h2>a")
  public WebElement elemSecondItem;
  @FindBy(css = "#feature-bullets>ul")
  public WebElement txtAboutItem;
  @FindBy(css = "#feature-bullets>h1")
  public WebElement txtAboutItemHeader;

  public AmazonPage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }
  @Step("Filter with specific brand")
  public void filterSpecificBrand(String filterName){ 
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
  @Step("Hamburger Menu Item Functionality")
   public void clickHamBurgerMenuItem(){
    this.hamburgerMenu.click();
   }
   @Step("Choose from Category")
   public void clickCategoryItem(){
    this.elemShopByCategory.click();
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    //this.waitForElement(driver, 5L, elemTelevision);
   }
   @Step("Choose Television Menu")
   public void clickTelevisionMenu(){
    this.elemTelevision.click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   }
   @Step("Sort by High to Low")
   public void sortBy(){
    this.elemSortBy.click();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    this.elemHighToLow.click();
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
    driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    String aboutThisItemHeader = this.txtAboutItemHeader.getText();
    return aboutThisItemHeader;
   }
   public void waitForElement(WebDriver driver,long Timeout,By elemTelevision2){
    WebDriverWait wait = new WebDriverWait(driver,Timeout);
    wait.until(ExpectedConditions.visibilityOfElementLocated(elemTelevision2));
   }
   public void scrollElementIntoView(WebElement element){
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].scrollIntoView(true);",element);
   }
   public void switchToWindow(){
    String amazonWindowParent=driver.getWindowHandle();
    Set<String>productDetailsWindows=driver.getWindowHandles();
    Iterator<String> I1= productDetailsWindows.iterator();
    while(I1.hasNext())
    {
    String child_window=I1.next();
    if(!amazonWindowParent.equals(child_window)){
        driver.switchTo().window(child_window);
    }
}
   }  
}
