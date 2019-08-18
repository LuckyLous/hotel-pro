package us.luckylu.dev.client.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author lu
 * @date 2019-06-15 9:34
 */
@Configuration
public class RabbitConfig {

    public final static String EXCHANGE_NAME = "normal-confirm-exchange";

    public final static String EXCHANGE_DIRECT_TYPE = "direct";

    public final static String EXCHANGE_TOPIC_TYPE = "topic";

}
