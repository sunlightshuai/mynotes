package com.mydata.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void debug(String logMessage){
        if(logger.isDebugEnabled()){
            logger.debug(logMessage);
        }
    }

    public static void error(String logMessage){
        if (logger.isErrorEnabled()){
            logger.error(logMessage);
        }
    }

    public static void info(String logMessage){
        if (logger.isInfoEnabled()){
            logger.info(logMessage);
        }
    }

    public static void trace(String logMessage){
        if (logger.isTraceEnabled()){
            logger.trace(logMessage);
        }
    }

}
