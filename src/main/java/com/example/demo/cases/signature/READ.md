接口签名校验说明文档
1、整体思路
前端保存被授权分配的常量appid和appsecret
前端在请求每一个接口时都在请求头中加入appid,nonce,timestamp和signature参数。
其中nonce为随机生成的uuid用于校验是否重复提交。
signature:appid=xxx&appsecret=xxx&nonce=xxx&timestamp=xxx&key1=xxx&key2=xxx&key3=xxx（key1,key2,key3按字典排序）

后端在项目启动时获取常量appid,appsecret,enableAllInterfaceSignature,resubmit四个参数，其中enableAllInterfaceSignature表示是否开启全局校验，resubmit表示是否允许重复提交。
在切面中，取得请求头里的appid获得后端对应的appsecret，然后将appid,nonce,timestamp,appsecret与请求参数一起加密与请求头里的signature进行对比。
2、配置
在配置文件中加入wowjoy.validate.enableAllInterfaceSignature=true,wowjoy.validate.resubmit=true即开启全局的校验。
如果只需要部分接口做校验还可以这样配置：
①在类上使用注解@InterfaceSignature即对类里的所有接口都做校验
②在接口上使用@InterfaceSignature即对该接口做校验
如果配置了全局校验则无需配置局部校验。