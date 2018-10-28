package Amzonurl1;

import com.sun.jna.platform.FileUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.awt.geom.Area;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * Created by 52592 on 2018/10/21.
 */
public class ExcelPipreline implements Pipeline{

    static HSSFWorkbook wb = new HSSFWorkbook();

    static HSSFSheet sheet = wb.createSheet("Amazon数据");  //创建table工作薄

    public void process(ResultItems resultItems, Task task) {
        Object result = resultItems.get("result");

        List<Map<String,String>> rows = (List<Map<String,String>>) result;

//        Map<String,String> roww = rows.get(1);
//        System.out.println(roww);



//        HSSFWorkbook wb = new HSSFWorkbook();
//        HSSFSheet sheet = wb.createSheet("table");  //创建table工作薄
//        Object[][] datas = {{"区域", "总销售额(万元)", "总利润(万元)简单的表格"}, {"江苏省" , 9045,  2256}, {"广东省", 3000, 690}};
//        HSSFRow row;
//        HSSFCell cell;
//        for(int i = 0; i < datas.length; i++) {
//            row = sheet.createRow(i);//创建表格行
//            for(int j = 0; j < datas[i].length; j++) {
//                cell = row.createCell(j);//根据表格行创建单元格
//                cell.setCellValue(String.valueOf(datas[i][j]));
//            }
//        }
//        try {
//            wb.write(new FileOutputStream("E:/result.xlsx"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        public class WriteExcel {


//            public static void main(String[] args) {

        try {
            writeExcel(rows, 5, "./AmazonResult_"+resultItems.get("keyword")+".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void writeExcel(List<Map<String, String>> dataList, int cloumnCount, String finalXlsxPath) throws IOException {
        int columnNumCount = cloumnCount;
//        File excelFiled=new File(finalXlsxPath);
//        if(!excelFiled.exists()){
//            excelFiled.c
//        }

//
//        HSSFWorkbook wb = new HSSFWorkbook();
//
//        HSSFSheet sheet = wb.createSheet("Amazon数据2");  //创建table工作薄
        for (int j = 0; j < dataList.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
//            HSSFRow row = sheet.createRow(j + 1);
            int rowNum=sheet.getLastRowNum();
            HSSFRow row = sheet.createRow(rowNum+1);
            Map<String,String> row_1 = dataList.get(j);

            String keyword=row_1.get("keyword").toString();
            String Result=row_1.get("result").toString();
            String Weight=row_1.get("weight").toString();
            String Asin=row_1.get("asin").toString();
            String Posion=row_1.get("positon").toString();
               for (int k = 0; k <= columnNumCount; k++) {
                // 在一行内循环
                HSSFCell first=row.createCell(0);
                first.setCellValue(keyword);

                HSSFCell second = row.createCell(1);
                second.setCellValue(Result);

                HSSFCell third = row.createCell(2);
                third.setCellValue(Weight);

                HSSFCell fors = row.createCell(3);
                fors.setCellValue(Asin);

                HSSFCell fai = row.createCell(4);
                fai.setCellValue(Posion);
//                   wb.write(new FileOutputStream(finalXlsxPath));

            }
//            wb.write(new FileOutputStream(finalXlsxPath,true));
        }

        wb.write(new FileOutputStream(finalXlsxPath));


    }





       /*     public static void writeExcel(List<Map<String, String>> dataList, int cloumnCount, String finalXlsxPath){
                OutputStream out = null;

                try {
                    // 获取总列数
                    int columnNumCount = cloumnCount;
                    // 读取Excel文档
                    File finalXlsxFile = new File(finalXlsxPath);
//                    Workbook workBook = getWorkbok(finalXlsxFile);
                    // sheet 对应一个工作页
                    HSSFWorkbook workbook = new HSSFWorkbook();

                HSSFSheet sheet = workbook.createSheet("table");
                    //Sheet sheet = workBook.getSheetAt(0);

                    /**
                     * 删除原有数据，除了属性列
                     */
                  /* int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
                    System.out.println("原始数据总行数，除属性列：" + rowNumber);
                    for (int i = 1; i <= rowNumber; i++) {
                        HSSFRow row = sheet.getRow(i);
                        sheet.removeRow(row);
                    }
                    // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
                    out =  new FileOutputStream(finalXlsxPath);
                    workbook.write(out);

                    /**
                     * 往Excel中写新数据
                     */
                 /*   for (int j = 0; j < dataList.size(); j++) {
                        // 创建一行：从第二行开始，跳过属性列
                        Row row = sheet.createRow(j + 1);
                        // 得到要插入的每一条记录
//                        Map<String, String> dataMap=new HashMap<String, String>();
//                        dataMap.put("BankName", "BankName");
//                        dataMap.put("Addr", "Addr");
//                        dataMap.put("Phone", "Phone");


//                        Map dataMap = dataList.get(j);
//                        String name = dataMap.get("BankName").toString();
//                        String address = dataMap.get("Addr").toString();
//                        String phone = dataMap.get("Phone").toString();
//                        row1.put("weight",ss.split("/")[9]);
//                        row1.put("asin",ss.split("/")[11]);
//                        row1.put("positon",ss.split("/")[12].split("\\?")[0]);
//                        row1.put("keyword",keyWord);
//                        row1.put("result",results);

//                        Map<String,String> row1=dataList.get(j);
                       Map<String,String> row_1 = dataList.get(j);

                        String keyword=row_1.get("keyWord").toString();
                        String Result=row_1.get("result").toString();
                        String Weight=row_1.get("weight").toString();
                        String Asin=row_1.get("asin").toString();
                        String Posion=row_1.get("position").toString();


                        for (int k = 0; k <= columnNumCount; k++) {
                            // 在一行内循环
                            Cell first = row.createCell(0);
                            first.setCellValue(keyword);

                            Cell second = row.createCell(1);
                            second.setCellValue(Result);

                            Cell third = row.createCell(2);
                            third.setCellValue(Weight);

                            Cell fors = row.createCell(3);
                            fors.setCellValue(Asin);

                            Cell fai = row.createCell(4);
                            fai.setCellValue(Posion);
                        }
                    }
                    // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
                    out =  new FileOutputStream(finalXlsxPath);
                    workbook.write(out);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    try {
                        if(out != null){
                            out.flush();
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("数据导出成功");
            }

            /**
             * 判断Excel的版本,获取Workbook
             * @param in
             * @param filename
             * @return
             * @throws IOException
             */
        /*  public static Workbook getWorkbok(File file) throws IOException{
                Workbook wb = null;
                FileInputStream in = new FileInputStream(file);
                if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
                    wb = new HSSFWorkbook(in);
                }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
                    wb = new XSSFWorkbook(in);
                }
                return wb;
               }  */
        }


