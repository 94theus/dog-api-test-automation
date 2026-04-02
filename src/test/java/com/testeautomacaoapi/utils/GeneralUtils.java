package com.testeautomacaoapi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.ITestResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtils {

    private GeneralUtils() {
    }

    public static String getNowDate(String mask) {
        DateFormat dateFormat = new SimpleDateFormat(mask);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getAllStackTrace(ITestResult result) {
        if (result == null || result.getThrowable() == null) {
            return "Stack trace não disponível.";
        }

        String allStackTrace = "";

        for (StackTraceElement stackTrace : result.getThrowable().getStackTrace()) {
            allStackTrace = allStackTrace + "<br>" + stackTrace.toString();
        }

        return allStackTrace;
    }

    public static String formatJson(Object object) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        return gson.toJson(object);
    }
}