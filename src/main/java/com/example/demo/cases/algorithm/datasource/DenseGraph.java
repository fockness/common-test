package com.example.demo.cases.algorithm.datasource;

import java.util.Vector;

/**
 * 稠密图
 */
public class DenseGraph {

    private Integer vertex, edge;//顶点、边
    private Boolean directed;//是否有向
    private Vector<Vector<Boolean>> g = new Vector<>();

    public DenseGraph(Integer vertex, Boolean directed){
        this.vertex = vertex;
        this.edge = 0;
        this.directed = directed;
        for(Integer i = 0; i<vertex; i++){
            g.add(new Vector<>(vertex));//?
        }
    }

    public Integer getVertex(){
        return vertex;
    }

    public Integer getEdge(){
        return edge;
    }

    /**
     * 将两个顶点间加边
     * @param vertex1
     * @param vertex2
     */
    public void addEdge(Integer vertex1, Integer vertex2){
        if(vertex1 >=0 && vertex1 <= vertex && vertex2 >=0 && vertex2 <= vertex){
            return;
        }
        //g[vertex1][vertex2] = true;
        if(!directed){
       //     g[vertex2][vertex1] = true;
        }
        vertex++;
    }
}
