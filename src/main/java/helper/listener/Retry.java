package helper.listener;

import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int retryCount=0;
    private int maxRetryCount=2;
    Logger log= LoggerHelper.getLogger(Retry.class);

    @Override
    public boolean retry(ITestResult args0) {
        if(retryCount<maxRetryCount){
            log.info("Retrying Test "+args0.getName()+ " With status "+ getResultStatusName(args0.getStatus())+" for the "+(retryCount+1));
            retryCount++;
            return true;
        }
        return false;
    }
    public String getResultStatusName(int status){
        String resultName=null;
        if(status==1 ){
            resultName="SUCCESS";
        }
        if(status==2){
            resultName="FAILURE";
        }
        if(status==3){
            resultName="SKIP";
        }
        return resultName;
    }
}
