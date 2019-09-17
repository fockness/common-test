package com.example.demo.cases.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.lang.reflect.Field;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-9-16 11:21
 * 4
 */
@BTrace
public class BtraceDebugger2 {

    /**
     * 命令为：Btrace -cp "E:\Code\uama-allinone\uama-microservices-base\uama
     * -microservices-api-base\target\classes" 9324 BtraceDebugger2.java
     * @param probeClassName
     * @param probeMethodName
     * @param obj
     */
    @OnMethod(clazz = "com.uama.microservices.provider.base.web.v1.OrgInfoProvider"
            , method = "addTest", location = @Location(value = Kind.ENTRY))
    public static void addTest(@ProbeClassName String probeClassName
            , @ProbeMethodName String probeMethodName
                                                    //, AnyType[] args  //被拦截的方法的参数值，不适合获取对象参数
            , MOrgInfoSearchF obj//想获取对象参数直接用这个对象
            ){
        BTraceUtils.println("getAllAssetStatistics");
        BTraceUtils.println("probeClassName:" + probeClassName);
        BTraceUtils.println("probeMethodName:" + probeMethodName);
        Field orgName = BTraceUtils.field("com.uama.microservices.api.base.model.form.orgcomplaint.MOrgInfoSearchF", "orgName");
        Field mobileNum = BTraceUtils.field("com.uama.microservices.api.base.model.form.orgcomplaint.MOrgInfoSearchF", "mobileNum");
        BTraceUtils.print(BTraceUtils.get(orgName, obj));
        BTraceUtils.print(BTraceUtils.get(mobileNum, obj));
    }

    /**
     * 查看对象的实例属性值
     * @param obj
     */
    @OnMethod(clazz = "com.uama.microservices.provider.base.web.v1.OrgInfoProvider"
            , method = "addTest", location = @Location(value = Kind.ENTRY))
    public static void addTest2(@Self Object obj){
        Field field = BTraceUtils.field("com.uama.microservices.provider.base.web.v1.OrgInfoProvider", "orgInfoService");
        BTraceUtils.print(BTraceUtils.get(field, obj));
    }
}
