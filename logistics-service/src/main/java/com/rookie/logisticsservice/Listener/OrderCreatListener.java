package com.rookie.logisticsservice.Listener;

import com.rookie.orderservice.model.OrderInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "order_queue")
public class OrderCreatListener {
    /**
     * RabbitHandler会根据参数类型进行匹配，不同的类型会调用不同的处理方法
     * @param msg
     */
    @RabbitHandler
    public void process(String msg) {
        System.out.println("物流端接收到消息String: " + msg);
    }
    @RabbitHandler
    public void process2(OrderInfo msg) {
        System.out.println("物流端接收到消息OrderInfo: " + msg);
    }
}
