package com.twittersystem.config;

import com.twittersystem.interceptor.SystemInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xiaoQe
 * @version 1.0
 * @data 2023/3/12 16:22
 */
@Configuration
public class MyCorsConfig implements WebMvcConfigurer {

    private final SystemInterceptor systemInterceptor;

    public MyCorsConfig(SystemInterceptor systemInterceptor){
        this.systemInterceptor = systemInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","HEAD","PUT","DELETE","OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    /**
     * @description: 配置拦截器
     * @author xiaoQe
     * @date 2023/4/22 14:26
     * @version 1.0
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(systemInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/system/*","/*","/webjars/**","/swagger-resources/**");
    }
}
