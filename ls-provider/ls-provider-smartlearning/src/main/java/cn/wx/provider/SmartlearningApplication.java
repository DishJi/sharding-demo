package cn.wx.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author nk
 * @date 2020/7/31
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.wx.provider.smartlearning.mapper.**")
@EnableDiscoveryClient
public class SmartlearningApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(SmartlearningApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //官网给了两种写法， 这里不要用classpath*
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
