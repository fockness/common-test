package com.example.demo.cases.algorithm.datasource;

import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏图--邻接表
 */
public class SparseGraph {
    private int n;  // 图中节点数量
    private int m;  // 图中边的数量
    private List<Integer>[] g;  // 邻接表 g
    private boolean directed;   // 是否为有向图

    public SparseGraph(int n, boolean directed) {
        this.n = n; // 初始化节点数量
        this.m = 0; // 初始图中边数为 0
        this.directed = directed;

        // 初始化一个用于存储 ArrayList<Integer>类型数据 的数组 g 作为邻接表
        g = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
    }

    // 返回图中边的数量
    public int E() {
        return m;
    }

    // 返回图中节点的数量
    public int V() {
        return n;
    }

    // 在节点 v 与 w 之间添加一条边
    public void addEdge(int v, int w) {

        // 当 v 和 w 之间没有边时
        if (!hasEdge(v, w)) {

            // 在邻接表中添加一条 v 、 w 之间的边
            g[v].add(w);
            if (v != w && !directed)
                g[w].add(v);

            // 图中边数+1
            m++;
        }
    }

    // 判断两节点 v、w 之间是否存在边
    private boolean hasEdge(int v, int w) {
        // 遍历 g[i] 中存储的 ArrayList，若其中存在 w ，返回 true
        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].get(i) == w)
                return true;
        }
        return false;
    }

    // 返回节点v 的所有邻接节点
    public Iterable<Integer> adjacentNode(int v){
        return g[v];
    }
}
