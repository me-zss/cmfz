package com.shun.util;

import com.shun.entity.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * 日志记录的工具类
 */
public class LogUtil {
    /**
     * 讲日志存入某个文件
     * @param file 文件绝对路径
     * @param logStr 日志内容
     */
    public static void addToText(File file, String logStr) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(file,true));
        pw.println(logStr);
        pw.close();
    }
}
