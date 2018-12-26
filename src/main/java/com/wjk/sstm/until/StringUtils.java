package com.wjk.sstm.until;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class StringUtils {
    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }

        return ("".equals(object.toString().trim()));
    }

    public static boolean isNullOrEmpty(String str) {
        if (null == str || str.trim().isEmpty())
            return true;
        return false;
    }

    public static boolean isNullOrEmpty(String... str) {

        for (int i=0,length=str.length;i<length;i++){
            if (null == str[i] || str[i].trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullorEmptyList(List list) {
        if (null == list || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static boolean isDigit(String text, int length) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return (pattern.matcher(text).matches()) && (text.length() == length);
    }

    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}