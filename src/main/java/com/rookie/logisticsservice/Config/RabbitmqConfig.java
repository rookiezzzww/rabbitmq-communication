package com.rookie.logisticsservice.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean("orderQueue")
    public Queue orderQueue() {
        return QueueBuilder.durable("order_queue").build();
    }
    //让 RabbitTemplate 在发送消息时，自动把 Java 对象转换成 JSON 格式，
    // 而不是用默认的 Java 序列化。
    @Bean
    public JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        //JacksonJsonMessageConverter是jackson3，只反解析受信任的消息，所以要把包名传到解析器受信任的包内
        return new JacksonJsonMessageConverter("com.rookie.orderservice.model");
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonJsonMessageConverter());
        return rabbitTemplate;
    }
}
