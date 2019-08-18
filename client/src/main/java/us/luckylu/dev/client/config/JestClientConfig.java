package us.luckylu.dev.client.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lu
 * @create 2019-03-25 16:56
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.elasticsearch.jest", ignoreUnknownFields = false)
public class JestClientConfig {

    private String uris;

    private Integer connectionTimeout;

    private Integer readTimeout;

    private Boolean multiThreaded;

    @Bean(name = "jestClient")
    public JestClient getJestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(uris)
                .connTimeout(connectionTimeout)
                .readTimeout(readTimeout)
                .multiThreaded(multiThreaded).build());
        return factory.getObject();
    }
}
