package com.core.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxExample {
    public static void main(String[] args) {
        //String url = "Admin/Journal?yearMonth=(\\d{5})";

        String template = "Admin/Journal\\?yearMonth=(\\d{5})";
        Pattern pattern = Pattern.compile(template);
        Matcher matcher = pattern.matcher("Admin/Journal?yearMonth=202505");
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }
}
