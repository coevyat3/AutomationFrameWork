package helper.assertion;



import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.Assert;

public class AssertionHelper {
    private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);

    public static void verifyText(String s,String t){
        log.info("verifying test: "+s+" with "+t);
        Assert.assertEquals(s,t);

    }
    public static void makeTrue(){
        log.info("Making script pass..");
       Assert.assertTrue(true);
    }
    public static void makeTrue(String msg){
        log.info("Making script pass..");
        Assert.assertTrue(true,msg);
    }
    public static void makeFalse(){
        log.info("Making script fail");
        Assert.assertTrue(false);
    }
    public static void makeFalse(String msg){
        log.info("Making script Fail "+msg);
        Assert.assertTrue(false,msg);
    }
    public static void verifyTrue(boolean status){
        Assert.assertTrue(status);
    }
    public static void verifyFalse(boolean status){
        Assert.assertFalse(status);
    }
    public static void verifyNotNullObject(String msg){
        log.info("Verify object is not null");
        Assert.assertNotNull(msg);
    }
    public static void verifyNull(String msg){
        log.info("Verify object is null");
        Assert.assertNull(msg);
    }
}
