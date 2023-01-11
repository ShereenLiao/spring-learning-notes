package com.code.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringmvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

//public class ServletContainers extends AbstractDispatcherServletInitializer {
//
//    //加载springmvc容器配置类
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        AnnotationConfigWebApplicationContext rootContext
//                = new AnnotationConfigWebApplicationContext();
//        rootContext.register(SpringmvcConfig.class);
//        return rootContext;
//    }
//    //设置那些请求归属spring mvc处理
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/save"};
//    }
//    //加载spring容器配置，不需要spring 容器的时候，return null就可以，因为现在开始用springmvc
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(SpringConfig.class);
//        return ctx;
//    }
//}
