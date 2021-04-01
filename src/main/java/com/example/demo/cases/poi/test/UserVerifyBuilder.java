package com.example.demo.cases.poi.test;

import com.github.stupdit1t.excel.verify.AbstractCellValueVerify;
import com.github.stupdit1t.excel.verify.AbstractVerifyBuidler;
import com.github.stupdit1t.excel.verify.CellVerifyEntity;
import com.github.stupdit1t.excel.verify.StringVerify;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 格式校验
 */
public class UserVerifyBuilder extends AbstractVerifyBuidler {

    private static UserVerifyBuilder builder = new UserVerifyBuilder();

    public static UserVerifyBuilder getInstance() {
        return builder;
    }

    @Override
    protected List<CellVerifyEntity> buildRule() {
        List<CellVerifyEntity> cellEntitys = new ArrayList<>();
        cellEntitys.add(new CellVerifyEntity("name", "A", new StringVerify("姓名", UserCellValueVerify.getInstance(), true)));
        cellEntitys.add(new CellVerifyEntity("age", "B", new StringVerify("年龄", true)));
        cellEntitys.add(new CellVerifyEntity("score", "C", new StringVerify("分数", true)));
        cellEntitys.add(new CellVerifyEntity("address", "D", new StringVerify("地址", true)));
        return cellEntitys;
    }

    static class UserCellValueVerify extends AbstractCellValueVerify{

        private static  UserCellValueVerify userCellValueVerify = new UserCellValueVerify();

        public static UserCellValueVerify getInstance(){
            return userCellValueVerify;
        }

        @Override
        public Object verify(Object o) throws Exception {
            String tar = null;
            if(o instanceof String){
                tar = (String)o;
            }
            if(StringUtils.isNotBlank(tar) && !tar.equals("11")){
                throw new RuntimeException("11111");
            }
            return null;
        }
    }
}
