package com.oppo.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean
 *
 * 调用地方
 * org.springframework.context.support.ApplicationContextAwareProcessor#invokeAwareInterfaces(java.lang.Object)
 */
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ApplicationContextUtil.applicationContext = context;
    }

    private static ApplicationContext getApplicationContext() {
        return ApplicationContextUtil.applicationContext;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取Spring核心配置文件路径
     *
     * @return
     */
    public static String[] getCoreConfigXmlPath(){
        return ((XmlWebApplicationContext)getApplicationContext()).getConfigLocations();
    }

    /**
     * 获取Spring容器所有的bean
     *
     * @return arr
     */
    public static String[] getAllBean(){
        return getApplicationContext().getBeanDefinitionNames();
    }

    /**
     * 获取微服务应用名字，例如"/lesson03"
     *
     * @return str
     */
    public static String getApplicationName(){
        return getApplicationContext().getApplicationName();
    }
}
