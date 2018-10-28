package com.workfree.spider.demand1;

/**
 * Created by 52592 on 2018/10/28.
 */
public class WDWUtil {
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
