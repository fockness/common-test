package com.example.demo.cases.algorithm.datasource;

/**
 * 并查集第1版
 */
public class UnionFind1 implements UF{

    private Integer[] id;

    public UnionFind1(Integer size){
        id = new Integer[size];
        for(Integer i=0; i<id.length; i++){
            id[i] = i;
        }
    }

    @Override
    public Integer getSize() {
        return id.length;
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

    /**
     * 合并元素p和q所属的集合
     *
     * 0 1 2 3 4 5 6 7 8 10 id的下标
     * 1 1 1 0 1 0 0 1 1 0  id(如果相同说明在同一个集合)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(Integer p, Integer q) {
        Integer pId = find(p);
        Integer qId = find(q);
        if(pId == qId){
            return;
        }
        for(Integer i=0; i<id.length; i++){
            if(id[i] == pId){
                id[i] = qId;
            }
        }

    }

    /**
     * 查找元素p所对应的集合编号
     * @param p
     * @return
     */
    private Integer find(Integer p){
        if(p < 0 || p > id.length){
            throw new RuntimeException("out of bounds");
        }
        return id[p];
    }
}

/**
 * 并查集的基本接口
 */
interface UF{
    Integer getSize();
    Boolean isConnected(Integer p, Integer q);
    void unionElements(Integer p, Integer q);
}
