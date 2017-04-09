package cc.moondust.config;

import com.taobao.api.DefaultTaobaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * Created by Tristan on 17/4/9.
 */
@Configuration
@PropertySources({@PropertySource(value = "classpath:api.properties", ignoreResourceNotFound = true)})
public class BeanFactory {
    @Value("${alibaba.url}")
    String url;
    @Value("${alibaba.appkey}")
    String appKey;

    @Value("${alibaba.secret}")
    String secret;


    @Bean("defaultTaobaoClient")
    public DefaultTaobaoClient buildTaobaoClient() {
        return new DefaultTaobaoClient(url, appKey, secret);
    }


    @Bean("gridFsTemplate")
    public GridFsTemplate buildGridFsTemplate(@Autowired MongoDbFactory dbFactory, @Autowired MongoConverter converter) {
        return new GridFsTemplate(
                dbFactory,
                converter
        );
    }


}
