package com.example.demo.cases.algorithm.search;

import com.example.demo.cases.algorithm.datasource.Tree;

public class TreeTools {

    /**
     * 判断树中节点个数
     * @param node
     * @return
     */
    public static Integer getTreeNum(Tree.TreeNode node){
        if(node == null){
            return 0;
        }
        return getTreeNum(node.getLeftNode()) + getTreeNum(node.getRightNode()) + 1;
    }

    /**
     * 获得深度
     * @param node
     * @return
     */
    public static Integer getTreeDepth(Tree.TreeNode node){
        if(node == null){
            return 0;
        }
        int leftDepth = getTreeDepth(node.getLeftNode()) + 1;
        int rightDepth = getTreeDepth(node.getRightNode()) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 前序遍历:中左右
     */
    public static void preOrderTravel(Tree.TreeNode node){
        if(node == null){
            return;
        }
        System.out.println(node.getValue());
        preOrderTravel(node.getLeftNode());
        preOrderTravel(node.getRightNode());
    }

    /**
     * 中序遍历:左中右
     * @param node
     */
    public static void midOrderTravel(Tree.TreeNode node){
        if(node == null){
            return;
        }
        midOrderTravel(node.getLeftNode());
        System.out.println(node.getValue());
        midOrderTravel(node.getRightNode());
    }

    /**
     *
     * 后序遍历:左右中
     * @param node
     */
    public static void backOrderTravel(Tree.TreeNode node){
        if(node == null){
            return;
        }
        backOrderTravel(node.getLeftNode());
        backOrderTravel(node.getRightNode());
        System.out.println(node.getValue());
    }

    /**
     * 分层遍历
     * @param node
     */
    public static void levelTravel(Tree.TreeNode node){
                
    }

}
