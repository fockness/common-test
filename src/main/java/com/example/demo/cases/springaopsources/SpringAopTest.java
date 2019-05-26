package com.example.demo.cases.springaopsources;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 这个类用来追溯aop的源码
 *  追溯执行流程：
 *      org.springframework.context.support.AbstractApplicationContext#getBean(java.lang.Class)
 *         org.springframework.context.support.AbstractRefreshableApplicationContext#getBeanFactory()-----返回DefaultListableBeanFactory
 *             org.springframework.beans.factory.support.DefaultListableBeanFactory#getBean(java.lang.Class, java.lang.Object...)
 *               org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveBean(org.springframework.core.ResolvableType, java.lang.Object[], boolean)
 *org.springframework.beans.factory.support.DefaultListableBeanFactory#resolveNamedBean(org.springframework.core.ResolvableType, java.lang.Object[], boolean)
 *org.springframework.beans.factory.support.AbstractBeanFactory#getBean(java.lang.String, java.lang.Class, java.lang.Object...)
 *org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean(java.lang.String, java.lang.Class, java.lang.Object[], boolean)----------容器初始化和getBean时都会进入这个方法
 *org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#getSingleton(java.lang.String, boolean)--- Object singletonObject = this.singletonObjects.get(beanName);取出的是一个代理类，说明在某个地方put了进去
 *找什么时候讲代理类put进singletonObjects中
 * org.springframework.context.annotation.AnnotationConfigApplicationContext#AnnotationConfigApplicationContext(java.lang.Class...)
 *org.springframework.context.support.AbstractApplicationContext#refresh()
 * org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
 * org.springframework.beans.factory.support.DefaultListableBeanFactory#preInstantiateSingletons()
 *这里断了，不知道什么时候put进去就全部搜一下singletonObjects.put
 * org.springframework.beans.factory.support.DefaultSingletonBeanRegistry#addSingleton(java.lang.String, java.lang.Object)-----为什么会调用getBean，因为在创建时需要查看一下是否有这个bean了
 * 190org.springframework.beans.factory.support.AbstractBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
 * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
 * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])
 * exposedObject
 * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)
 * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(java.lang.Object, java.lang.String)
 * org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#postProcessAfterInitialization(java.lang.Object, java.lang.String)
 * 重要org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#wrapIfNecessary(java.lang.Object, java.lang.String, java.lang.Object)
 * 重要org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#createProxy(java.lang.Class, java.lang.String, java.lang.Object[], org.springframework.aop.TargetSource)
 * 重要org.springframework.aop.framework.ProxyFactory#getProxy(java.lang.ClassLoader)
 * 重要org.springframework.aop.framework.ProxyCreatorSupport#createAopProxy()
 *org.springframework.aop.framework.DefaultAopProxyFactory#createAopProxy(org.springframework.aop.framework.AdvisedSupport)
 */
public class SpringAopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(Object.class);
        TestDao testDao = annotationConfigApplicationContext.getBean(TestDao.class);
        testDao.say();
    }
}

class TestDao{
    public void say(){
        System.out.println("say");
    }
}

class TestAspect{

}