package com.example.demo.cases.poi.test;

import com.example.demo.cases.poi.ProjectVerifyBuilder;
import com.github.stupdit1t.excel.common.ImportRspInfo;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.List;

public class CollectErrorExcelTest {

    public static void main(String[] args) throws Exception {
// 1.获取源文件
        Workbook wb = WorkbookFactory.create(new FileInputStream("src\\test\\java\\excel\\imports\\import.xls"));
        // 2.获取sheet0导入
        Sheet sheet = wb.getSheetAt(0);
        // 3.生成VO实体
        List<ErrorData<User>> errorData = Lists.newArrayList();
        ImportRspInfo<User> list = ExcelUtils.parseSheet(User.class, new ProjectVerifyBuilder(), sheet, 3, 0
                , (row, rowNum)->{

                }, errorData);
        if (list.isSuccess()) {
            //throw new RuntimeException("1");
            // 导入没有错误，打印数据
            //System.out.println(list.getData().size());
        } else if(!list.isSuccess() && errorData.size() > 0){
            // 导入有错误，打印输出错误
            System.out.println(list.getMessage());
        }

    }
}
