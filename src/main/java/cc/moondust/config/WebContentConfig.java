package cc.moondust.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * Created by MIKU on 2017/3/18.
 */
@Configuration
public class WebContentConfig extends WebMvcConfigurerAdapter {


//    @Bean("multipartResolver")
//    public MultipartResolver buildMultipartResolver() {
//        CommonsMultipartResolver res = new CommonsMultipartResolver();
//        res.setMaxUploadSize(1024 * 1024 * 10);
//        res.setDefaultEncoding("UTF-8");
//        return res;
//    }
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(1024 * 1024 * 10);
//
//        return factory;
//    }


}