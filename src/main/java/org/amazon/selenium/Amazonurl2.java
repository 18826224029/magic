package org.amazon.selenium;

/**
 * Created by 52592 on 2018/10/26.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Amazonurl2 {


    public static void main(String[] args) throws InterruptedException {


//        System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator.6CHJ255ZLFAIQE0\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");//chromedriver服务地址
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        WebDriver driver = new ChromeDriver(); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动
//        driver.get("https://www.baidu.com");
//        driver.findElement(By.id("kw")).sendKeys("你好");
//        Thread.sleep(5000);
//        driver.findElement(By.id("su")).click();
//        Thread.sleep(5000);
//        WebElement button = driver.findElement(By.className(""));
//        button.click();
//        WebElement textEle = driver.findElement(By.id(""));
//        String url = textEle.getAttribute("href");
//        String text = textEle.getText();


//        driver.get("https://www.amazon.com/Fancii-Lighted-Travel-Makeup-Magnification/dp/B01MXSU6P9/ref=sr_1_1_a_it?ie=UTF8&qid=1539705965&sr=8-1&keywords=B01MXSU6P9");//打开指定的网站
          driver.get("https://www.amazon.com");
          driver.findElement(By.id("twotabsearchtextbox")).sendKeys("B01MXSU6P9");
          driver.findElement(By.className("nav-input")).click();
        WebElement link1 = driver.findElement(By.className("a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal"));
        String clink = link1.getAttribute("href");
        driver.get(clink);
        for (int i=1;i<22;i++){
            Thread.sleep(3000);
        driver.findElement(By.id("a-autoid-13")).click();}







        try {
            /**
             * WebDriver自带了一个智能等待的方法。
             dr.manage().timeouts().implicitlyWait(arg0, arg1）；
             Arg0：等待的时间长度，int 类型 ；
             Arg1：等待时间的单位 TimeUnit.SECONDS 一般用秒作为单位。
             */
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }
}
