package com.example.demo.cases.algorithm.datasource;

import com.example.demo.cases.util.Check;

/**
 *
 */
public class Tree {

    public class TreeNode<T> {
        private T value;
        private TreeNode leftNode;
        private TreeNode rightNode;

        public TreeNode() {
        }

        public TreeNode(T value) {
            this.value = value;
        }

        public <T> void addLeft(T value) {
            Check.isNull(value, "value is not allowed null");
            TreeNode<T> leftNode = new TreeNode<>(value);
            this.leftNode = leftNode;
        }

        public <T> void addRight(T value){
            Check.isNull(value, "value is not allowed  null");
            TreeNode<T> rightNode = new TreeNode<>(value);
            this.rightNode = rightNode;
        }

        public boolean equals(Object obj){
            if(obj instanceof TreeNode){
                return this.value.equals(((TreeNode) obj).value);
            }
            return false;
        }
    }
}
