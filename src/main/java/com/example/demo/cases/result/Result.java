package com.example.demo.cases.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import static com.example.demo.cases.result.Code.SUCCESS;

@Data
@AllArgsConstructor
public class Result<T> {

    private String code;

    private String message;

    private T data;

    private static final Result OK = new Result(SUCCESS);

    Result(Code code, String message){
        this.code = code.getCode();
        this.message = message;
    }

    Result(Code code){
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static Result ok(){
        return OK;
    }

    public static <T> Result<T> ok(T data){
        return new Result(SUCCESS.getCode(), null, data);
    }

    public static void main(String[] args) {
        Result result = Result.ok(111);
        System.out.println(result);
    }
}
