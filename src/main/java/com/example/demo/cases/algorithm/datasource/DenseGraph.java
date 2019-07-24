package com.example.demo.cases.algorithm.datasource;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-7-24 19:26
 * 4稠密图--邻接矩阵
 */
public class DenseGraph {

    private Integer n, m;//顶点，边
    private Boolean isDirected;
    private ArrayList<ArrayList<Edge>> g = new ArrayList<>();

    /**
     * 构建一个邻接矩阵
     * @param n
     * @param isDirected
     */
    public DenseGraph(Integer n, Boolean isDirected){
        this.n = n;
        this.m = 0;
        this.isDirected = isDirected;
        for(Integer i=0; i<n; i++){
            ArrayList<Edge> list = new ArrayList<>();
            for(Integer j=0; j<n; j++){
                list.add(new Edge());
            }
            g.add(list);
        }
    }

    public int V(){
        return n;
    }

    public int E(){
        return m;
    }

    public void addEdge(Integer v, Integer w, Integer weight){
        hasEdge(v, w);
        ArrayList<Edge> edges = g.get(v);
        edges.set(w, new Edge(v, w, weight));
        if(!isDirected){//如果不是有向图，则镜面也要加
            ArrayList<Edge> edges1 = g.get(w);
            edges1.set(v, new Edge(w, v, weight));
        }
        m++;
    }

    public Boolean hasEdge(Integer v, Integer w){
        if(v  <= 0 && v > n && w <= n && w > n){
            throw new RuntimeException("越界");
        }
        ArrayList<Edge> edges = g.get(v);
        Edge edge = edges.get(w);
        return Objects.nonNull(edge);
    }

    public ArrayList<Edge> getGraph(int n){
        return g.get(n);
    }


    @Data
    private static class Edge{
        private int a,b;
        private int weight;
        private boolean isEmpty;
        public Edge() {
            this.isEmpty=true;
        }

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        public int v(){
            return a;
        }
        public int w(){
            return b;
        }
        public int wt(){
            return weight;
        }
        public int other(int x){
            assert(x==a||x==b);
            return x==a?b:a;
        }

        public boolean isEmpty(){
            return this.isEmpty;
        }
    }

    public static void main(String[] args) {
        int N=20;  //顶点
        int M=100;   //边
        DenseGraph sg=new DenseGraph(N,false);
        for(int i=0;i<M;i++){
            sg.addEdge(new Random().nextInt(N), new Random().nextInt(N),new Random().nextInt(10));//权值[0,10)
        }
        System.out.print("  ");
        for(int i=0;i<N;i++){
            String s=i>=10?(" "+i):("  "+i);
            System.out.print(s);
        }
        System.out.println();
        for(int i=0;i<N;i++){
            System.out.print(i+" ");
            ArrayList<Edge> srr=sg.getGraph(i);
            Iterator<Edge> ite=srr.iterator();
            if(i<10)
                System.out.print(" ");
            while(ite.hasNext()){
                System.out.print(" "+ite.next().wt()+" ");
            }
            System.out.println();
        }
    }
}


