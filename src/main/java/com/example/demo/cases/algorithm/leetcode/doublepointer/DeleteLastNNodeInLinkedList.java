package com.example.demo.cases.algorithm.leetcode.doublepointer;

import lombok.Data;
import org.junit.Test;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-7-15 19:33
 * 4
 */
public class DeleteLastNNodeInLinkedList {

    @Test
    public void testDelete(){
        Node node1 = new Node();
        node1.setValue(1);
        Node node2 = new Node();
        node2.setValue(2);
        Node node3 = new Node();
        node3.setValue(3);
        node1.setNext(node2);
        node2.setNext(node3);
        delete(node1, 2);
        Node beforeHead = new Node();
        beforeHead.next = node1;
        while(beforeHead.next != null){
            System.out.println(beforeHead.value);
            beforeHead = beforeHead.next;
        }
    }

    private void delete(Node head, Integer n){
        Node beforeHead = new Node();
        beforeHead.setNext(head);
        Node first = beforeHead;//步长为n+1
        Node last = beforeHead;
        for(int i=1; i<n+1; i++){
            first = first.next;
        }
        while(first != null){
            first = first.next;
            last = last.next;
        }
        last.next = last.next.next;
    }


    @Data
    private static class Node{
        private Integer value;
        private Node next;

        public void setNext(Node node){
            this.next = node;
        }
    }
}
