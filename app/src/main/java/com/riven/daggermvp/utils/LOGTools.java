package com.riven.daggermvp.utils;

import android.text.TextUtils;

import com.riven.daggermvp.MyApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description: 日志存储到本地
 * Author: yq
 * Date: 2017/12/21
 */
public class LOGTools {
    private static String path = MyApp.getInstance().getExternalFilesDir("log").getAbsolutePath();
    private static Executor singleThreadPool = Executors.newSingleThreadExecutor();

    public static void writeLog(final Throwable ex){
        singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                Writer writer = new StringWriter();
                PrintWriter printWriter = new PrintWriter(writer);
                ex.printStackTrace(printWriter);
                Throwable cause = ex.getCause();
                while (cause != null) {
                    cause.printStackTrace(printWriter);
                    cause = cause.getCause();
                }
                printWriter.close();
                String result = writer.toString();
                writeLogSave(result);
            }
        });
    }

    public static void writeLog(final String text){
        singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                writeLogSave(text);
            }
        });
    }


    private static void writeLogSave(String result){
        if (TextUtils.isEmpty(result)){
            return;
        }

        File dir = new File(path);
        if (!dir.exists()){
         boolean dirResult = dir.mkdirs();
        }

        File file = new File(path + File.separator + "log.log");
        if (!file.exists()){
            try {
                boolean fielResult = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file,true);
            String date = AppDateTools.getDateToString(System.currentTimeMillis(), AppDateTools.DATE_TIME_FORMAT);
            fileOutputStream.write(date.getBytes("utf-8"));
            fileOutputStream.write("  ------  ".getBytes("utf-8"));
            fileOutputStream.write(result.getBytes("utf-8"));
            fileOutputStream.write("\r\n".getBytes("utf-8"));
            fileOutputStream.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != fileOutputStream){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
