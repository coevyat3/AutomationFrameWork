package helper.dropdown;


import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;


public class DropDownHelper {
    private WebDriver driver;
    private Logger log= LoggerHelper.getLogger(DropDownHelper.class);

    public DropDownHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void selectByValue(WebElement element,String value){
        Select select= new Select(element);
        select.selectByValue(value);
        log.info("Select  by his value "+value);
    }
    public void selectByIndex(WebElement element,int index){
        Select select= new Select(element);
        select.selectByIndex(index);
        log.info("Select by index "+index);
    }
    public void selectByVisibleText(WebElement element,String value){
        Select select= new Select(element);
        select.selectByVisibleText(value);
        log.info("Select By visible text "+value);

    }
    public void deselectByValue(WebElement element,String value){
        Select select= new Select(element);
        select.deselectByValue(value);
        log.info("Deselect by  value: "+value);
    }
    public void deselectByIndex(WebElement element,int index){
        Select select= new Select(element);
        select.deselectByIndex(index);
        log.info("Deselect by index "+index);
    }
    public List<String>getDropDownData(WebElement element){
        Select select= new Select(element);
        List<WebElement> elementList=select.getOptions();
        List<String>valueList=new LinkedList<>();
        for(WebElement element1:elementList){
            log.info(element1.getText());
            valueList.add(element1.getText());
        }return valueList;
    }

}
