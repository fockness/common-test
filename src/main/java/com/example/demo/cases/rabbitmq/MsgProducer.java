//package com.example.demo.cases.rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.UUID;
//
////@Component
//@Slf4j
//public class MsgProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
//
//    private RabbitTemplate rabbitTemplate;
//
//    public MsgProducer(@Autowired RabbitTemplate rabbitTemplate){
//        this.rabbitTemplate = rabbitTemplate;
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }
//
//    public void sendMsg(String exchange,  String routingKey, String content){
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        rabbitTemplate.convertAndSend(exchange, routingKey, content, correlationId);
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String s) {
//        log.info("回调的confirm.id" + correlationData);
//        if(ack){
//            log.info("消息确认");
//        }else{
//            log.info("消息失败");
//        }
//    }
//
//    @Override
//    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
//
//    }
//}
