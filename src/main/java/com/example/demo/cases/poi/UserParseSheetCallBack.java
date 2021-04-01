package com.example.demo.cases.poi;

import com.example.demo.cases.poi.test.User;
import com.github.stupdit1t.excel.callback.ParseSheetCallback;

/**
 * 数据校验
 */
public class UserParseSheetCallBack implements ParseSheetCallback<User> {

    private static UserParseSheetCallBack instance = new UserParseSheetCallBack();

    public static UserParseSheetCallBack getInstance(){
        return instance;
    }

    @Override
    public void callback(User user, int i) throws Exception {
        if(!user.getAddress().equals("22222")){
            throw new RuntimeException(i+"hhhhh");
        }
    }
}
