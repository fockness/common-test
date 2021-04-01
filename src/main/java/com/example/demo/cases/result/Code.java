package com.example.demo.cases.result;

public enum Code {

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    ;

    private String code;

    private String message;

    Code(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
