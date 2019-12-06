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


}
