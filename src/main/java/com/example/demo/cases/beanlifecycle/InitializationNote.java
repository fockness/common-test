package com.example.demo.cases.beanlifecycle;

import org.springframework.beans.BeansException;

public class InitializationNote {

    //AbstractApplicationContext
    public void refresh() throws BeansException, IllegalStateException {
        //   添加一个synchronized 防止出现refresh还没有完成出现其他的操作（启动，或者销毁）
        synchronized (this.startupShutdownMonitor) {
            // 1.准备工作
            // 记录下容器的启动时间、
            // 标记“已启动”状态，关闭状态为false、
            // 加载当前系统属性到环境对象中
            // 准备一系列监听器以及事件集合对象
            prepareRefresh();

            // 2. 创建容器对象：DefaultListableBeanFactory，加载XML配置文件的属性到当前的工厂中（默认用命名空间来解析），
            // 就是上面说的BeanDefinition（bean的定义信息）这里还没有初始化，只是配置信息都提取出来了，（包含里面的value值其实都只是占位符）
            ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

            // 3. BeanFactory的准备工作，设置BeanFactory的类加载器，添加几个BeanPostProcessor，手动注册几个特殊的bean等
            prepareBeanFactory(beanFactory);
            try {
                // 4.子类的覆盖方法做额外的处理，就是我们刚开始说的 BeanFactoryPostProcessor ，具体的子类可以在这步的时候添加一些特殊的
                // BeanFactoryPostProcessor完成对beanFactory修改或者扩展。
                // 到这里的时候，所有的Bean都加载、注册完成了，但是都还没有初始化
                postProcessBeanFactory(beanFactory);
                // 5.调用 BeanFactoryPostProcessor 各个实现类的 postProcessBeanFactory(factory) 方法
                invokeBeanFactoryPostProcessors(beanFactory);

                // 6.注册 BeanPostProcessor  处理器 这里只是注册功能，真正的调用的是getBean方法
                registerBeanPostProcessors(beanFactory);

                // 7.初始化当前 ApplicationContext 的 MessageSource，即国际化处理
                initMessageSource();

                // 8.初始化当前 ApplicationContext 的事件广播器，
                initApplicationEventMulticaster();

                // 9.从方法名就可以知道，典型的模板方法(钩子方法)，感兴趣的同学还可以再去复习一下之前写的设计模式中的-模版方法模式
                //  具体的子类可以在这里初始化一些特殊的Bean（在初始化 singleton beans 之前）
                onRefresh();

                // 10.注册事件监听器，监听器需要实现 ApplicationListener 接口。这也不是我们的重点，过
                registerListeners();

                // 11.初始化所有的 singleton beans（lazy-init 的除外），重点关注
                finishBeanFactoryInitialization(beanFactory);

                // 12.广播事件，ApplicationContext 初始化完成
                finishRefresh();
            }
            catch (BeansException ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Exception encountered during context initialization - " +
                            "cancelling refresh attempt: " + ex);
                }
                // 13.销毁已经初始化的 singleton 的 Beans，以免有些 bean 会一直占用资源
                destroyBeans();
                cancelRefresh(ex);
                // 把异常往外抛
                throw ex;
            }
            finally {
                // Reset common introspection caches in Spring's core, since we
                // might not ever need metadata for singleton beans anymore...
                resetCommonCaches();
            }
        }
    }
}
