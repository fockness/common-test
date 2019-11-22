package com.example.demo.cases.common.autowired;

import com.example.demo.cases.common.ConsumerTest;
import com.example.demo.cases.common.FunctionTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class BaseController {

    @Autowired
    protected FunctionTest functionTest;

    @Autowired
    protected ConsumerTest consumerTest;

    @Value("com.test.value")
    protected String value;
}
