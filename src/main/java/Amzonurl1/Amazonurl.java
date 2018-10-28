package Amzonurl1;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class Amazonurl implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);

    Set<String> pageNumSet = new HashSet<String>();

    private   static String keyWord ;

    public Amazonurl(String keyWord) {
        this.keyWord = keyWord;
    }

    public void process(Page page) {
        List<Map> rows =new ArrayList<Map>();
        Map<String,String> row1=null;
//        Map<String,String> row1 = new HashMap<String, String>();
      //  String results=page.getHtml().xpath("//span[@id='s-result-count']/text())").toString();
      String results=page.getHtml().xpath("//*[@id=\"s-result-count\"]/text()").toString();
        List<Selectable> abc = page.getHtml().xpath("//div[@id='pagn']/span").nodes();
        System.out.println(abc);
        for(Selectable selectable:abc){
            Selectable aa = selectable.links();
            System.out.println(aa);
        }



        for (Selectable selectable : abc) {
            Selectable pageHref = selectable.xpath("//a").links();
          //  System.out.println("pageHref------------"+pageHref);
            Selectable pageText = selectable.xpath("//a/text()");
           // System.out.println("pageText----"+pageText);
            String pageUrl = pageHref.toString();

                if (pageHref.toString() != null && !pageNumSet.contains(pageText.toString())) {

                    pageNumSet.add(pageText.toString());


                    page.addTargetRequest(pageHref.toString());

//                   System.out.println("pageHref"+pageHref.toString());
//                    System.out.println("pageText"+pageText.toString());

            }
        }
        System.out.println("results:"+results);

       List<Selectable> lis = page.getHtml().xpath("//li[contains(@id,'result_')]").nodes();

         for(Selectable selectable:lis){
              row1 = new HashMap<String, String>();
           Selectable a =selectable.xpath("//div[@class='a-row a-spacing-small']//a").links();
             if(a.toString()==null){
              //   System.out.println("找到了null"+selectable.toString());
                 continue;
             }
             if(a.toString().startsWith("https://www.amazon.com/gp/")){
               //  System.out.println("有广告"+a.toString());
                 String ss = null;
                 try {
                    ss=URLDecoder.decode(a.toString(),"UTF-8");
                 } catch (UnsupportedEncodingException e) {
                     e.printStackTrace();
                 }
                // System.out.println("有广告新的"+ss);//9 11 12

                 row1.put("weight",ss.split("/")[9]);
                 row1.put("asin",ss.split("/")[11]);
                 row1.put("positon",ss.split("/")[12].split("\\?")[0]);
                 row1.put("keyword",keyWord);
                 row1.put("result",results);
                 System.out.println("有广告权重词:"+ss.split("/")[9]);
                 System.out.println("有广告Asin:"+ss.split("/")[11]);
                 System.out.println("有广告位置:"+ss.split("/")[12].split("\\?")[0]);
                 System.out.println("\n\n\n");

                  System.out.println(a.toString().split("/")[5]);

                 //continue;
             }
             else {

                 System.out.println("权重词:"+a.toString().split("/")[3]);
                 System.out.println("Asin:"+a.toString().split("/")[5]);


                 System.out.println("位置:"+a.toString().split("/")[6].split("\\?")[0]);

                 System.out.println("" + "\n\n\n");

                 row1.put("weight",a.toString().split("/")[3]);
                 row1.put("asin",a.toString().split("/")[5]);
                 row1.put("positon",a.toString().split("/")[6].split("\\?")[0]);
                 row1.put("keyword",keyWord);
                 row1.put("result",results);
             }
             rows.add(row1);
         }
        page.putField("result",rows);


    }
//    public static int add(int i,int k){
//            System.out.println("");
//            return i+k;
//    }

    public Site getSite() {
        return site;
    }


    public void run(){
        //       Spider.create(new Am()).addUrl("https://www.amazon.com").thread(3).run();
        Spider.create(this).addPipeline(new ExcelPipreline()).addUrl("https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords="+keyWord).thread(1).run();
//        add(1,2);
    }
}



