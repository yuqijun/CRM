package com.hwua.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrenDate {
    public static String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        return date;
    }
}
