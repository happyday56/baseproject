package com.huotu.duobao.config;

import com.huotu.common.api.ApiResultHandler;
import com.huotu.common.api.OutputHandler;

import com.huotu.huobanplus.sdk.common.CommonClientSpringConfig;
import com.huotu.huobanplus.sdk.mall.MallSDKConfig;
import org.luffy.lib.libspring.data.ClassicsRepositoryFactoryBean;
import org.luffy.lib.libspring.logging.LoggingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lgh on 2016/1/12.
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.huotu.duobao.controller", "com.huotu.duobao.service"})
@EnableJpaRepositories(value = {"com.huotu.duobao.repository"}, repositoryFactoryBeanClass = ClassicsRepositoryFactoryBean.class)
@ImportResource(value = {"classpath:spring-jpa.xml"})
@Import(value = {CommonClientSpringConfig.class, MallSDKConfig.class, LoggingConfig.class})
@EnableScheduling
public class MVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }

    @Autowired
    private CommonInterceptor commonInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/app/*");
    }

    /**
     * 设置控制器方法参数化输出
     *
     * @param argumentResolvers
     */
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new OutputHandler());
    }

    /**
     * 监听 控制器的ApiResult返回值
     *
     * @param returnValueHandlers
     */
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new ApiResultHandler());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new AppHandlerExceptionResolver());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
        registry.viewResolver(viewResolver());
        registry.jsp();
    }

    /**
     * for upload
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        SpringTemplateEngine engine = new SpringTemplateEngine();
        ServletContextTemplateResolver rootTemplateResolver = new ServletContextTemplateResolver();
        rootTemplateResolver.setPrefix("/");
        rootTemplateResolver.setSuffix(".html");
        rootTemplateResolver.setCharacterEncoding("UTF-8");

        // start cache
//        if(env.acceptsProfiles("dev")){
//            System.out.println("Develement Mode");
//            rootTemplateResolver.setCacheable(false);
//        }

        engine.setTemplateResolver(rootTemplateResolver);

        resolver.setTemplateEngine(engine);
//        resolver.setOrder(99);
        resolver.setOrder(2147483647 + 10);
//        resolver.setViewNames(new String[]{"*.html"});
        resolver.setCharacterEncoding("UTF-8");
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
        resolver.setExcludedViewNames(new String[]{
                "content/**"
        });
        return resolver;
    }


}
