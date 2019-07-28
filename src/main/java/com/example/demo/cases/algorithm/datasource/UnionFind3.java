package com.example.demo.cases.algorithm.datasource;

/**
 * 并查集第3版
 */
public class UnionFind3 implements UF {

    private Integer[] parent;
    private Integer[] sz;

    public UnionFind3(Integer size){
        parent = new Integer[size];
        for(Integer i=0; i<parent.length; i++){
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public Integer getSize() {
        return parent.length;
    }

    /**
     * 用于查看元素p和q是否属于同一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public Boolean isConnected(Integer p, Integer q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(Integer p, Integer q) {
        Integer pRoot = find(p);
        Integer qRoot = find(q);
        if(pRoot == qRoot){
            return;
        }
        if(sz[pRoot] < sz[qRoot]){
            //让p的根节点指向q的根节点
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    /**
     * 找到p节点的根节点
     * o(h)时间复杂度，h为树的高度
     * @param p
     * @return
     */
    private Integer find(Integer p){
        if(p < 0 || p > parent.length){
            throw new RuntimeException("out of bounds");
        }
        while(p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
