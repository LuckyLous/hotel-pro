package us.luckylu.dev.client.config;

import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author lu
 * @create 2019-03-25 16:56
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "es.properties")
public class TransportClientConfig {

    private String host;

    private Integer port;

    public String clusterName = "";

    public String analyzer;

    private Settings.Builder settings = Settings.builder().put("cluster.name", clusterName);

    @Bean(name = "transportClient")
    public TransportClient transportClient(){
        TransportClient client = null;
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return client;
    }
}
