package com.example.demo.cases.btrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-7 13:53
 * 4
 */
@BTrace
public class BtraceDebugger {

    /**
     * 监控某个方法的运行时间,并拿到方法的返回值
     * @param probeClassName
     * @param probeMethodName
     * @param duration
     */
    @OnMethod(clazz = "+com.uama.microservices.provider.base.service.businessregion.ISysBusinessRegionService"
            , method = "getBusinessRegionList", location = @Location(value = Kind.RETURN))
    public static void printMethodRuntime(@ProbeClassName String probeClassName
            , @ProbeMethodName String probeMethodName
            , @Duration long duration
            , AnyType[] args  //被拦截的方法的参数值
            , @Self Object object
            , @Return AnyType result){//实际中可以放在目标项目中使用该项目下的对象作为返回值
        BTraceUtils.println("printMethodRuntime");
        BTraceUtils.println("probeClassName:" + probeClassName);
        BTraceUtils.println("probeMethodName:" + probeMethodName);
        BTraceUtils.println("runtime:" + duration/1000000 + "ms");
        BTraceUtils.println("result:" + result);
        BTraceUtils.println("object:" + object);
        BTraceUtils.printArray(args);
    }

    /**
     * 监控方法的入口
     * @param probeClassName
     * @param probeMethodName
     * @param args
     * @param object
     */
    @OnMethod(clazz = "+com.uama.microservices.provider.base.service.businessregion.ISysBusinessRegionService"
            , method = "getBusinessRegionList", location = @Location(value = Kind.ENTRY))
    public static void printMethodParameters(@ProbeClassName String probeClassName
            , @ProbeMethodName String probeMethodName
            , AnyType[] args  //被拦截的方法的参数值
            , @Self Object object){//实际中可以放在目标项目中使用该项目下的对象作为返回值，在这个例子中即打印SysBusinessRegionServiceImpl对象
        BTraceUtils.println("printMethodParameters");
        BTraceUtils.println("probeClassName:" + probeClassName);
        BTraceUtils.println("probeMethodName:" + probeMethodName);
        BTraceUtils.println("object:" + object);;
//        Field field = BTraceUtils.field("com.uama.framework.core.PageBean", "dataList");
//        BTraceUtils.println("objectDetails:" + BTraceUtils.get(field, object));
        BTraceUtils.println("============");
        BTraceUtils.printArray(args);
        BTraceUtils.println("======jstack======");
        BTraceUtils.jstack();//分析哪个方法调用了System.gc()，调用栈如何
    }

    /**
     * 监控某行是否执行了(未生效)
     * @param probeClassName
     * @param probeMethodName
     * @param line
     */
    @OnMethod(clazz = "com.uama.microservices.provider.base.service.impl.businessregion.SysBusinessRegionServiceImpl"
            , method = "getBusinessRegionList", location = @Location(value = Kind.LINE, line = 377))
    public static void traceLine(@ProbeClassName String probeClassName, @ProbeMethodName String probeMethodName, Integer line){
        BTraceUtils.println(probeClassName + "," + probeMethodName + "," + line);
    }

    /**
     * 查看某个子方法是否被调用
     * @param pcm
     * @param pmn
     * @param instance
     * @param method
     */
    @OnMethod(
            clazz = "com.uama.microservices.provider.base.web.v1.OrgInfoProvider",
            method = "addTest",
            location = @Location(value = Kind.CALL, clazz = "/.*/", method = "add")
    )
    public static void trace3(@ProbeClassName String pcm, @ProbeMethodName String pmn,
                              @TargetInstance Object instance, @TargetMethodOrField String method) {
        BTraceUtils.println("ProbeClassName:" + pcm);//父名
        BTraceUtils.println("ProbeMethodName:" + pmn);//父方法
        BTraceUtils.println("TargetInstance: "+ instance);//被调用的子方法所在类名，这里就是OrgInfoProvider
        BTraceUtils.println("TargetMethodOrField : "+ method);//目标方法里的子方法add
    }
}
