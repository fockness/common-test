package com.example.demo.cases.poi;

import com.alibaba.fastjson.JSON;
import com.example.demo.cases.poi.test.User;
import com.example.demo.cases.poi.test.UserVerifyBuilder;
import com.github.stupdit1t.excel.Column;
import com.github.stupdit1t.excel.ExcelUtils;
import com.github.stupdit1t.excel.callback.ParseSheetCallback;
import com.github.stupdit1t.excel.common.ImportRspInfo;
import com.github.stupdit1t.excel.common.POIException;
import com.github.stupdit1t.excel.verify.AbstractVerifyBuidler;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class PoiController {

    @PostMapping("/test/poi/upload")
    public void excelUpload2(@RequestParam("file") MultipartFile file) throws IOException {
        // 1.获取源文件
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        // 2.获取sheet0导入
        Sheet sheet = wb.getSheetAt(0);
        // 3.生成VO数据
        //参数：1.生成VO的class类型;2.校验规则;3.导入的sheet;3.从第几行导入;4.尾部非数据行数量;5.导入每条数据的回调
        ImportRspInfo<ProjectEvaluate2> list = ExcelUtils.parseSheet(ProjectEvaluate2.class, ProjectVerifyBuilder.getInstance(), sheet, 1, 0, (row, rowNum) -> {
            //1.此处可以完成更多的校验
            if(row.getName().equals("中青旅")){
                throw new POIException("第"+rowNum+"行，区域名字不能为中青旅！");
            }
            //2.图片导入，再ProjectEvaluate定义类型为byte[]的属性就可以，ProjectVerifyBuilder定义ImgVerfiy校验列.就OK了
        });
        if (list.isSuccess()) {
            // 导入没有错误，打印数据
            System.out.println(JSON.toJSONString(list.getData()));
            //打印图片byte数组长度
            String img = list.getData().get(0).getAge();
            System.out.println(img);
        } else {
            // 导入有错误，打印输出错误
            System.out.println(list.getMessage());
        }
    }

    @PostMapping("/poi/download")
    public void excelDownload(HttpServletResponse response) throws IOException {
        // 1.导出的hearder设置
        List<Map<String, Object>> mapData = new ArrayList<>();
        Map<String, Object> obj = new HashMap<>();
        obj.put("name", "张三");
        obj.put("age", 5);
        mapData.add(obj);

        // 2.导出hearder对应的字段设置，列宽设置
        String[] hearder = {"姓名", "年龄"};
        Column[] column = {
                Column.field("name"),
                Column.field("age"),
        };
        // 3.执行导出到工作簿
        Workbook bean = ExcelUtils.createWorkbook(mapData, ExcelUtils.ExportRules.simpleRule(column, hearder));
        // 4.写出文件
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("1111" + ".xls","utf-8"));
        OutputStream outputStream = response.getOutputStream();
        bean.write(outputStream);
        outputStream.flush();
        outputStream.close();
        bean.close();
    }

    /**
     * 用户管理导入数据接口
     * @param file
     * @throws IOException
     */
    @PostMapping("/poi/upload")
    public void excelUpload(@RequestParam("file") MultipartFile file, @RequestBody ImportAction importAction) throws IOException {
        MappingEnum mappingEnum = MappingEnum.getByTarget(importAction.getTarget());
        MappingVerifier mappingVerifier = mappingEnum.mappingVerifier;
        // 1.获取源文件
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        // 2.获取sheet0导入
        Sheet sheet = wb.getSheetAt(importAction.getSheetStart());
        // 3.生成VO数据
        //参数：1.生成VO的class类型;2.校验规则;3.导入的sheet;3.从第几行导入;4.尾部非数据行数量;5.导入每条数据的回调
        ImportRspInfo<?> list = ExcelUtils.parseSheet(mappingVerifier.getClazz(), mappingVerifier.getBuilder(), sheet
                , importAction.getDataStartRow(), importAction.getDataEndRowCount(),
                mappingVerifier.getCallback());
        if (list.isSuccess()) {
            // 导入没有错误，打印数据
            System.out.println(JSON.toJSONString(list.getData()));
            //调用对应服务的feign
            ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            SuperClient client = (SuperClient)context.getBean(mappingVerifier.getFeign());
            client.save(null);
        } else {
            // 导入有错误，打印输出错误
            System.out.println(list.getMessage());
        }
    }

    /**
     * 用户导出接口参数
     */
    @GetMapping("/poi/download")
    public void excelDownload(@ModelAttribute ExportAction exportAction){
        MappingEnum mappingEnum = MappingEnum.getByTarget(exportAction.getTarget());
        MappingVerifier mappingVerifier = mappingEnum.mappingVerifier;
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        SuperClient client = (SuperClient)context.getBean(mappingVerifier.getFeign());
    }

    enum MappingEnum{
        USER_IMPORT("user-import", UserMappingEnum.USER_IMPORT),
        ;
        private String target;
        private MappingVerifier mappingVerifier;

        MappingEnum(String target, MappingVerifier mappingVerifier){
            this.target = target;
            this.mappingVerifier = mappingVerifier;
        }

        public static MappingEnum getByTarget(String target){
            for(MappingEnum enums : values()){
                if(enums.target.equals(target)){
                    return enums;
                }
            }
            return null;
        }
    }

    /**
     * 用户类导入
     */
    enum UserMappingEnum implements MappingVerifier{

        USER_IMPORT(User.class, UserVerifyBuilder.getInstance(), UserParseSheetCallBack.getInstance(), UserClient.class),;

        private Class<?> clazz;
        private AbstractVerifyBuidler builder;
        private ParseSheetCallback<?> callback;
        private Class<?> feign;

        UserMappingEnum(Class<?> clazz, AbstractVerifyBuidler builder, ParseSheetCallback<?> callback, Class<?> feign){
            this.clazz = clazz;
            this.builder = builder;
            this.callback = callback;
            this.feign = feign;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public AbstractVerifyBuidler getBuilder() {
            return builder;
        }

        public ParseSheetCallback<?> getCallback() {
            return callback;
        }

        public Class<?> getFeign() {
            return feign;
        }
    }

    interface MappingVerifier<T>{
        Class<?> getClazz();
        AbstractVerifyBuidler getBuilder();
        ParseSheetCallback<?> getCallback();
        Class<?> getFeign();
    }

    interface UserClient extends SuperClient{
        void saveUser();
    }

    interface SuperClient{
        void save(List<Map<String, String>> list);
        void download(Map<String, String> parameters);
    }
}

