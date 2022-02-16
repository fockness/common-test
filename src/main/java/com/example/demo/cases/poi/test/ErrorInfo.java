package com.example.demo.cases.poi.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    //字段名
    private String column;

    //数值
    private Object data;

    //错误信息
    private String errorMessage;
}
