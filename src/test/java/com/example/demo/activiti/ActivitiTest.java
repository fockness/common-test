package com.example.demo.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiTest{

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void testInit(){
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/testB.bpmn")
                .addClasspathResource("processes/testB.bpmn.png").name("测试流程").deploy();
        System.out.println("流程部署ID:"+deployment.getId());
        System.out.println("流程部署Name:"+deployment.getName());
    }

    @Test
    public void testStart(){
        ProcessInstance processInstance=runtimeService // 运行时流程实例Service
                .startProcessInstanceByKey("myFirstProcess"); // 流程定义表的KEY字段值
        System.out.println("流程实例ID:"+processInstance.getId());
        System.out.println("流程定义ID:"+processInstance.getProcessDefinitionId());
    }
}
