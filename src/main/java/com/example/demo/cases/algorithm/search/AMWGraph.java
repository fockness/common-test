package com.example.demo.cases.algorithm.search;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * ͼ��������ȱ���DFS�͹�����ȱ���BFS
 */
public class AMWGraph {
    private ArrayList vertexList;//�洢�������
    private int[][] edges;//�ڽӾ��������洢��
    private int numOfEdges;//�ߵ���Ŀ
    private boolean[] isVisited;//������ʾĳ���ڵ��Ƿ񱻱�����

    public AMWGraph(int n) {
        //��ʼ������һά���飬�ͱߵ���Ŀ
        edges=new int[n][n];
        vertexList=new ArrayList(n);
        numOfEdges=0;
        isVisited = new boolean[n];
    }
 
    //�õ����ĸ���
    public int getNumOfVertex() {
        return vertexList.size();
    }
 
    //�õ��ߵ���Ŀ
    public int getNumOfEdges() {
        return numOfEdges;
    }
 
    //���ؽ��i������
    public Object getValueByIndex(int i) {
        return vertexList.get(i);
    }
 
    //����v1,v2��Ȩֵ
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }
 
    //������
    public void insertVertex(Object vertex) {
        vertexList.add(vertexList.size(),vertex);
    }
 
    //�����
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2]=weight;
        numOfEdges++;
    }
 
    //ɾ����
    public void deleteEdge(int v1,int v2) {
        edges[v1][v2]=0;
        numOfEdges--;
    }
 
    //�õ���һ���ڽӽ����±�
    public int getFirstNeighbor(int index) {
        for(int j=0;j<vertexList.size();j++) {
            if (edges[index][j]>0) {
                return j;
            }
        }
        return -1;
    }
 
    //����ǰһ���ڽӽ����±���ȡ����һ���ڽӽ��
    public int getNextNeighbor(int v1,int v2) {
        for (int j=v2+1;j<vertexList.size();j++) {
            if (edges[v1][j]>0) {
                return j;
            }
        }
        return -1;
    }
     
    //˽�к�����������ȱ���
    private void depthFirstSearch(boolean[] isVisited,int  i) {
        //���ȷ��ʸý�㣬�ڿ���̨��ӡ����
        System.out.print(getValueByIndex(i)+"  ");
        //�øý��Ϊ�ѷ���
        isVisited[i]=true;
         
        int w=getFirstNeighbor(i);//
        while (w!=-1) {
            if (!isVisited[w]) {
                depthFirstSearch(isVisited,w);
            }
            w=getNextNeighbor(i, w);
        }
    }
     
    //���⹫��������������ȱ���������ͬ��˽�к������ڷ�������
    public void depthFirstSearch() {
        for(int i=0;i<getNumOfVertex();i++) {
            //��Ϊ���ڷ���ͨͼ��˵��������ͨ��һ������һ�����Ա������н��ġ�
            if (!isVisited[i]) {
                depthFirstSearch(isVisited,i);
            }
        }
    }
     
    //˽�к�����������ȱ���
    private void broadFirstSearch(boolean[] isVisited,int i) {
        int u,w;
        LinkedList queue=new LinkedList();
         
        //���ʽ��i
        System.out.print(getValueByIndex(i)+"  ");
        isVisited[i]=true;
        //��������
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u=((Integer)queue.removeFirst()).intValue();
            w=getFirstNeighbor(u);
            while(w!=-1) {
                if(!isVisited[w]) {
                        //���ʸý��
                        System.out.print(getValueByIndex(w)+"  ");
                        //����ѱ�����
                        isVisited[w]=true;
                        //�����
                        queue.addLast(w);
                }
                //Ѱ����һ���ڽӽ��
                w=getNextNeighbor(u, w);
            }
        }
    }
     
    //���⹫��������������ȱ���
    public void broadFirstSearch() {
        for(int i=0;i<getNumOfVertex();i++) {
            if(!isVisited[i]) {
                broadFirstSearch(isVisited, i);
            }
        }
    }

    public static void main(String args[]) {
        int n=8,e=9;//�ֱ�����������ͱߵ���Ŀ
        String labels[]={"1","2","3","4","5","6","7","8"};//���ı�ʶ
        AMWGraph graph=new AMWGraph(n);
        for(String label:labels) {
            graph.insertVertex(label);//������
        }
        //���������
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(7, 3, 1);
        graph.insertEdge(7, 4, 1);
        graph.insertEdge(6, 2, 1);
        graph.insertEdge(5, 2, 1);
        graph.insertEdge(6, 5, 1);

        System.out.println("���������������Ϊ��");
        graph.depthFirstSearch();
        System.out.println();
        System.out.println("���������������Ϊ��");
        graph.broadFirstSearch();
    }
}