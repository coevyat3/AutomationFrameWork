package helper.listener;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
       arg0.setRetryAnalyzer(Retry.class);


    }
}
