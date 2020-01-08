package com.baizhi.qs;

import com.baizhi.qs.dao.UserDao;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPoi {

    @Autowired
    UserDao userDao;

    @Test
    public void test1(){
        //1.创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建一个工作簿
        HSSFSheet sheet = workbook.createSheet();
        //3.创建行对象
        HSSFRow row = sheet.createRow(0);
        //4.创建单元格
        HSSFCell cell = row.createCell(1);
        //5.为单元格赋值
        cell.setCellValue("德玛西亚！");
        //6.将Excel文档做本地输出
        try {
            workbook.write(new File("D:\\百知教育\\第三阶段：框架\\后期项目\\aa\\Excel\\"+new Date().getTime()+".xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void dao(){
//        Integer integer = userDao.queryUserByTime("0",2);
//        System.out.println(integer + "---------------------------------------");
    }
}
