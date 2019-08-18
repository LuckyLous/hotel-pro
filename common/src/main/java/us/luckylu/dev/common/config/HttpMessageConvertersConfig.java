package us.luckylu.dev.common.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import us.luckylu.dev.common.model.serializer.IdLongToStrSerializer;

import java.util.ArrayList;

/**
 * SpringMVC的MessageConverter，修改json字符串的转换特性
 * 对@RequestBody、@ResponseBody的数据进行转化
 */
@Configuration
public class HttpMessageConvertersConfig {

    /**
     * JSON序列化
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 添加 fastjson 的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(new SerializerFeature[]{
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.PrettyFormat
        });

        // 全局配置Long类型转化为String
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.put(Long.class, new IdLongToStrSerializer());
        serializeConfig.put(Long.TYPE, new IdLongToStrSerializer());
        // 可以全局配置时间类的格式化，也可以全局配置所有的LocalDateTime类型转化为时间戳(通过实现ObjectSerializer)
        // 但是通常使用@JSONField来指定时间格式
        // fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // serializeConfig.put(LocalDateTime.class, new DateTimeToLongSerializer());
        fastJsonConfig.setSerializeConfig(serializeConfig);

        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 当 ContentType为application/json 时，此 converter 才会生效
        fastConverter.setSupportedMediaTypes(new ArrayList<MediaType>() {
            {
                add(MediaType.APPLICATION_JSON_UTF8);
                add(MediaType.APPLICATION_JSON);
            }
        });

        return new HttpMessageConverters(fastConverter);
    }

}
