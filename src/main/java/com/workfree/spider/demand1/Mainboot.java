package com.workfree.spider.demand1;

import Amzonurl1.Amazonurl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 52592 on 2018/10/28.
 */
public class Mainboot {

    public static void main(String[] args) {
        ImportExecl poi = new ImportExecl();
        List<String> keywords =new ArrayList<String>();
        // List<List<String>> list = poi.read("d:/aaa.xls");
        List<List<String>> list = poi.read(args[0]);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print("第" + (i) + "行");
                List<String> cellList = list.get(i);
                for (int j = 0; j < cellList.size(); j++) {
                    System.out.println(cellList.get(j));
                    keywords.add(cellList.get(j));
                }
                System.out.println();
            }

        }
        System.out.println("开始爬了哦");
        for(String keyword:keywords){
            Amazonurl amazonurl=new Amazonurl(keyword,args[1]);
            amazonurl.run();
        }

    }




}
