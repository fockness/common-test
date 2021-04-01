package com.example.demo.cases.poi.test;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
public class PoiTestController {

    /**
     *
     *
     * 导入的excel是否需要保留？存在哪里？
     * 思路：
     * 1、整一个excel服务，ui来依赖它，然后暴露导入导出接口
     * 2、在这个服务中，拥有所有数据库的访问权限，需要访问的表的mapper和do，下载到导出中心去。（或者依赖所有feign，调用feign去将数据存入到对应的服务中）
     *      需要建立一个标识符与mapper.method的对应关系才能知道是哪个业务的导入或导出
     *                  导入标识符举例：userinfo-校验器-mapper.method
     *                  导出标识符举例：参数(用flag-hashmap形式？)-userinfo-mapper.method
     * 3、导入
     *         -同步
     *
     *         -异步
     *              @Async
     *    导出
     *         -同步
     *         -异步
     *
     *  4、需要一个导入导出中心，导入导出成功会把结果写在这个列表里
     */

    /**
     * 大标题设置
     * @param response
     * @throws IOException
     */
    @PostMapping("/poi/test/create")
    public void test1(HttpServletResponse response) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();  //--->创建了一个excel文件
        HSSFSheet sheet = wb.createSheet("理财资金报表");   //--->创建了一个工作簿
        HSSFDataFormat format= wb.createDataFormat();   //--->单元格内容格式
        sheet.setColumnWidth((short)3, 20* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的
        sheet.setColumnWidth((short)4, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度
        sheet.setDefaultRowHeight((short)300);    // ---->有得时候你想设置统一单元格的高度，就用这个方法

        //样式1
        HSSFCellStyle style = wb.createCellStyle(); // 样式对象
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置标题字体格式
        Font font = wb.createFont();
        //设置字体样式
        font.setFontHeightInPoints((short)20);   //--->设置字体大小
        font.setFontName("Courier New");   //---》设置字体，是什么类型例如：宋体
        font.setItalic(true);     //--->设置是否是加粗
        style.setFont(font);     //--->将字体格式加入到style1中
        //style1.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());
        //style1.setFillPattern(CellStyle.SOLID_FOREGROUND);设置单元格颜色
        style.setWrapText(true);   //设置是否能够换行，能够换行为true
        style.setBorderBottom(BorderStyle.THIN);   //设置下划线，参数是黑线的宽度
        style.setBorderLeft(BorderStyle.THIN);   //设置左边框
        style.setBorderRight(BorderStyle.THIN);   //设置有边框
        style.setBorderTop(BorderStyle.THIN);   //设置下边框
        style.setDataFormat(format.getFormat("￥#,##0"));    //--->设置为单元格内容为货币格式

        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));    //--->设置单元格内容为百分数格式


        //表格第一行
        HSSFRow row1 = sheet.createRow(0);   //--->创建一行
        // 四个参数分别是：起始行，起始列，结束行，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 15));
        row1.setHeightInPoints(25);
        HSSFCell cell1 = row1.createCell((short)0);   //--->创建一个单元格
        cell1.setCellStyle(style);
        cell1.setCellValue("总公司资金运用日报明细表（理财资金）");

        /*//表格第二行
        sheet.addMergedRegion(new CellRangeAddress(1,(short)0,1,(short)15));
        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2 = row2.createCell((short)0);
        cell2.setCellValue("报告日期："+new Date());
        cell2.setCellStyle(style);*/
        close(response, wb);
    }

    private void close(HttpServletResponse response, HSSFWorkbook bean) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("12" + ".xls","utf-8"));
        OutputStream outputStream = response.getOutputStream();
        bean.write(outputStream);
        outputStream.flush();
        outputStream.close();
        bean.close();
    }
}
