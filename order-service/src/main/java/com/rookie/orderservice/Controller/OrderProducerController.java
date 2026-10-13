package com.rookie.orderservice.Controller;


import com.rookie.orderservice.model.OrderInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderProducerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RequestMapping("/create")
    public void create() {
        String msg = "order created";
        rabbitTemplate.convertAndSend("","order_queue", msg);
        System.out.println("订单创建并发送: " + msg);
    }
    @RequestMapping("/createOrderInfo")
    public void createOrderInfo() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId("123456789");
        orderInfo.setName("西瓜");
        orderInfo.setPrice(9999);
        rabbitTemplate.convertAndSend("","order_queue", orderInfo);
        System.out.println("订单创建并发送: " + orderInfo);
    }
}
