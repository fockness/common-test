package com.example.demo.cases.poi.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorData<T> {

    //错误行数据
    private T t;

    private List<ErrorInfo> errorInfos;
}
