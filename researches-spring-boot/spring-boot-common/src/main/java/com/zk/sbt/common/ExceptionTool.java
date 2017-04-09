package com.zk.sbt.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by qcon on 2017/3/28.
 */
public class ExceptionTool {

    public static String getStackTrace (Throwable throwable){
        StringWriter stringWriter = new StringWriter(1024);
        PrintWriter printWriter = new PrintWriter(stringWriter, true);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
