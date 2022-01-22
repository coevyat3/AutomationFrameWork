package buymeObjects;

import com.aventstack.extentreports.Status;
import helper.browserConfiguration.config.ObjectReader;
import helper.javascript.JavScriptHelper;
import helper.logger.LoggerHelper;
import helper.verification.VerificationHelper;
import helper.wait.WaitHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static testBase.TestBase.test;

public class NavBarPage {
    private WebDriver driver;

    private Logger log= LoggerHelper.getLogger(NavBarPage.class);

    public NavBarPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);


    }
    @FindBy(css="form.form.ember-view")
    private WebElement x;

    @FindBy(css="span.selected-text")
    private List<WebElement>list;

    @FindBy(css="div.dropdown li span")
    private List<WebElement>list2;

    @FindBy(css = "label>input[type='text']")
    private WebElement inputBox;

    @FindBy(css = "label[class*='show-options'] span")
    private List<WebElement> autoSuggestList;

    @FindBy(css = "a[rel*='nofo'][class*='bm']")
    private WebElement searchBtn;

    public SearchResultPage pickItem(String val1,String val2,String val3) {
        clickAmount();
        clickActiveList(val1);
        clickArea();
        clickActiveList(val2);
        clickCategory();
        clickActiveList(val3);
        clickSearchBtn();
        return new SearchResultPage(driver);

    }
    public void clickAmount(){
        log.info("Waiting for element "+ list.get(0)+" To be clickable" );
        test.log(Status.INFO,"Waiting for element "+ list.get(0).getText()+" To be clickable");
        new WaitHelper(driver).WaitForElementClickable(list.get(0),ObjectReader.reader.getExplicitWait() );
        log.info("Click on Amount navBar Btn: "+list.get(0).getText() );
        test.log(Status.INFO,"Click on Amount navBar Btn: "+list.get(0).getText());
        list.get(0).click();
    }
    public void clickArea(){
        log.info("Clicking on Area Element "+list.get(1).getText());
        test.log(Status.INFO,"Clicking on Area Element "+list.get(1).getText());
        list.get(1).click();

    }
    public void clickCategory(){
        log.info("Clicking On Category dropdown "+list.get(2).getText());
        test.log(Status.INFO,"Clicking On Category dropdown "+list.get(2).getText());
        list.get(2).click();
    }
    public void  clickActiveList(String value){
        log.info("click on "+value+" from dropdown");
        test.log(Status.INFO,"click on "+value+" from dropdown");
        for(WebElement element:list2){
            if(element.getText().contains(value)){
                element.click();
                 new VerificationHelper(driver).isSelected(element);
                break;
            }
        }

    }
    public void sendTextIntoBox(String txt){
        log.info("Search gift from textBox "+txt);
        test.log(Status.INFO,"Search gift from textBox "+txt);
        inputBox.sendKeys(txt);
    }
    public SearchResultPage searchFromInputBox(String txt)  {
        sendTextIntoBox(txt);
        clickSearchBtn();
        return new SearchResultPage(driver);
    }
    public void clickSearchBtn(){
        log.info("Click on Search me a gift Button "+searchBtn.getText());
        test.log(Status.INFO,"Click on Search me a gift Button "+searchBtn.getText());
        searchBtn.click();
    }
    public void searchFromInputBoxAutoComplete(String txt,String s){
        log.info("Search gift from textBox with auto complete suggest pick  with "+txt +" text ");
        test.log(Status.INFO,"Search gift from textBox with autocomplete pick "+txt + "text ");
        inputBox.sendKeys(txt);
        for(WebElement element:autoSuggestList){
            if(element.getText().contains(s)){
                Actions actions= new Actions(driver);
                actions.moveToElement(element).perform();
                element.click();
                log.info("Search gift from textBox with auto complete suggest pick "+txt +" and target text search is"+ s  );
                test.log(Status.INFO,"Search gift from textBox with autocomplete pick "+txt + "and target text search is"+ s);
                break;
            }


        }



    }





}
