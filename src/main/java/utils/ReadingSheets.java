package utils;

import helper.excel.ExcelHelper;
import helper.resource.ResourceHelper;

import java.util.ArrayList;

public class ReadingSheets {
    static ExcelHelper loginExcel;
    static ExcelHelper cartExcel;
    static String loginData="loginData";
    static String  cartData="giftCard";


    public static ArrayList<Object[]>getLoginData(){
        ArrayList<Object[]>data=new ArrayList<>();
        try{
            loginExcel = new ExcelHelper(ResourceHelper.getResourcePath("src/main/resources/dataprovider/Book1.xlsx"));
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i = 2; i<= loginExcel.getRowCount(loginData); i++){
            String email=loginExcel.getCellData(loginData,"email",i);
            String password=loginExcel.getCellData(loginData,"password",i);
            Object obj[]={email,password};
            data.add(obj);
        }
        return data;

    }
    public static ArrayList<Object[]>getCartData(){
        ArrayList<Object[]>data=new ArrayList<>();
        try{
            cartExcel = new ExcelHelper(ResourceHelper.getResourcePath("src/main/resources/dataprovider/Book1.xlsx"));
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i = 2; i<=cartExcel.getRowCount(cartData) ; i++){
           String item=cartExcel.getCellData(cartData,"type",i);
           Object obj[]= {item};
            data.add(obj);
        }
        return data;

    }

}
