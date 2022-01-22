package utils;

import helper.excel.ExcelHelper;
import helper.resource.ResourceHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;


public class CreatingSheets {
    static WebDriver driver;
    static ExcelHelper ex;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ex = new ExcelHelper(ResourceHelper.getResourcePath("src/main/resources/dataprovider/Book1.xlsx"));
        //   createAccountAndAddToSheet();
        //    createCartSheet();
        createTestSheet();


    }
    public static void createTestSheet(){
        String sheet="tests";
        String test="test";
        String status="status";
       if(!ex.isSheetExist("Tests")){
           ex.addSheet(sheet);
           ex.addColumn(sheet,test);
           ex.addColumn(sheet,status);
       }
    }

    public static void insertUserData(UserData ud) {
        String email = "email";
        String loginData = "loginData";
        String name = "name";
        String password = "password";
        if (!ex.isSheetExist(loginData)) {
            ex.addSheet(loginData);
            ex.addColumn(loginData, name);
            ex.addColumn(loginData, email);
            ex.addColumn(loginData, password);
        }
        int row = ex.getRowCount(loginData);
        ex.setCellData(loginData, name, row + 1, ud.getName());
        ex.setCellData(loginData, email, row + 1, ud.getEmail());
        ex.setCellData(loginData, password, row + 1, ud.getPassword());

    }

    public static void createAccountAndAddToSheet() throws InterruptedException {
        UserData ud;
        driver = new ChromeDriver();
        driver.get("https://buyme.co.il/");
        for (int i = 0; i <= 2; i++) {
            ud = new UserData();
            driver.findElement(By.cssSelector("li.notSigned>a")).click();
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div.register-or-login>Span")).click();
            driver.findElement(By.cssSelector("form[action='register'] input[type*='text']")).sendKeys(ud.getName());
            driver.findElement(By.cssSelector("form[action='register'] input[type*='email']")).sendKeys(ud.getEmail());
            driver.findElement(By.id("valPass")).sendKeys(ud.getPassword());
            driver.findElement(By.cssSelector("input[data-parsley-equalto]")).sendKeys(ud.getPassword());
            driver.findElement(By.cssSelector("div[class*='field']+button[type=submit]")).click();
            insertUserData(ud);
            Actions actions = new Actions(driver);
            Thread.sleep(3000);
            actions.moveToElement(driver.findElement(By.cssSelector("li.dropdown span[class*='arrow']"))).perform();
            driver.findElement(By.cssSelector("ul.menu>li:last-of-type>a")).click();
        }
        driver.quit();

    }

    public static void createCartSheet() {

        String giftCard = "giftCard";
        String type = "type";

        if (!ex.isSheetExist(giftCard)) {
            ex.addSheet(giftCard);
            ex.addColumn(giftCard, type);

        }
        List<String> list = new ArrayList<>();
        list.add("sport");
        list.add("Extreme");
        list.add("wine");
        int row = ex.getRowCount(giftCard);
        int i;
        for (i = 0; i < list.size(); i++) {
            ex.setCellData(giftCard, type, i + 2, list.get(i).toString());
            System.out.println(list.get(i).toString());
        }
    }


}






