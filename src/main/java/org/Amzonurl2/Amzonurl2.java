package org.Amzonurl2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Amzonurl2 implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);
    public static void main(String[] args) {
        Spider.create(new Amzonurl2()).addUrl("https://www.amazon.com/").thread(1).run();



    }

    public void process(Page page) {
        page.addTargetRequest("https://www.amazon.com/Fancii-Lighted-Travel-Makeup-Magnification/dp/B01MXSU6P9/ref=sr_1_1_a_it?ie=UTF8&qid=1539705965&sr=8-1&keywords=B01MXSU6P9");

        System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator.6CHJ255ZLFAIQE0\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");//chromedriver服务地址
        WebDriver driver =new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动

        driver.get("https://www.amazon.com/Fancii-Lighted-Travel-Makeup-Magnification/dp/B01MXSU6P9/ref=sr_1_1_a_it?ie=UTF8&qid=1539705965&sr=8-1&keywords=B01MXSU6P9");//打开指定的网站
//                driver.findElement(By.id("kw")).sendKeys(new  String[] {"hello"});//找到kw元素的id，然后输入hello
//                driver.findElement(By.id("su")).click(); //点击按扭


        for(int i=0;i<25;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Selectable> str2 = page.getHtml().xpath("//div[@id=\"sp_detail\"]//li[@class='a-carousel-card']").nodes();

//       System.out.println(str2);
            for (Selectable selectable : str2) {
//          System.out.println("yyyyy"+selectable);
                List<Selectable> str13 = selectable.xpath("//a").nodes();

                for(Selectable abc:str13){
                    Selectable stru = abc.links();
                    System.out.println(stru.toString());
                }
//            Selectable Sponsored = selectable.links();
                //            System.out.println("aaa"+Sponsored);
            }
            driver.findElement(By.id("a-autoid-12")).click();



        }
        try {
            /**
             * WebDriver自带了一个智能等待的方法。
             dr.manage().timeouts().implicitlyWait(arg0, arg1）；
             Arg0：等待的时间长度，int 类型 ；
             Arg1：等待时间的单位 TimeUnit.SECONDS 一般用秒作为单位。
             */
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }

    public Site getSite() {
        return site;
    }
}

