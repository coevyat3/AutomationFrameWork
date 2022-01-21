package helper.resource;


import helper.logger.LoggerHelper;
import org.apache.log4j.Logger;

public class ResourceHelper {
    private static Logger log= LoggerHelper.getLogger(ResourceHelper.class);

    public static String getResourcePath(String path){
        System.out.println(path);
             String basePath=System.getProperty("user.dir");
             return basePath+"/"+path;

    }




}
