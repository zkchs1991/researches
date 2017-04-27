package com.zk.sbt.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;

/**
 * Created by Qcon on 2017/4/21.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public GlobeInterceptor globeInterceptor (){
        return new GlobeInterceptor();
    }

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry){
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/");
    }

    /**
     *  这里没有使用呢registry.addInterceptor(new GlobeInterceptor())
     *  因为new的时候无法在interceptor中使用{@link org.springframework.beans.factory.annotation.Value}之类的Spring注入方式
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globeInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CharacterEncodingFilter());
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        registrationBean.setOrder(30);
        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registrationBean.setInitParameters(new HashMap<String, String>() {{
            put("encoding", "UTF-8");
        }});
        return registrationBean;
    }

    @Bean
    public ObjectMapper objectMapper (){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

}
