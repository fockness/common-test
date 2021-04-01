//package com.example.demo.cases.rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//
////@Component
////@RabbitListener(queues = QueueConstant.QUEUE_A)
//@Slf4j
//public class MsgReceiver {
//
//    @RabbitHandler
//    public void process(String content){
//        log.info("队列消息是：" + content);
//    }
//}
