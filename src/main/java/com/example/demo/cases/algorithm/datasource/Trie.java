package com.example.demo.cases.algorithm.datasource;

import java.util.TreeMap;

/**
 * 字典树
 * 用途：1适用查找是否含有某个单词前缀的业务
 * leetcode:208,211,677
 */
public class Trie {

    private class Node{
        private Boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(Boolean isWord){
            this.isWord = isWord;
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private Integer size;


    public Trie(){
        this.root = new Node();
        size = 0;
    }

    /**
     * 获得trie中存储的单词数量
     * @return
     */
    public Integer getSize(){
        return size;
    }

    /**
     * 向trie中添加一个新的单词
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(root.next.get(c) == null){
                root.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询trie中是否包含某个单词
     * @return
     */
    public Boolean contains(String word){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询trie中是否有以prefix为前缀的单词
     * @return
     */
    public Boolean isPrefix(String prefix){
        Node cur = root;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    /**
     *上面contains的加强版，可以匹配....
     * @return
     */
    public Boolean containsRegex(String word){
        return match(root, word, 0);
    }

    private Boolean match(Node node, String word, Integer index){
        if(word.length() == index){
            return node.isWord;
        }
        char c = word.charAt(index);
        if(c == '.'){
            for(char key : node.next.keySet()){
                if(match(node.next.get(key), word, index+1)){
                    return true;
                }
                return false;
            }
        }
        if(node.next.get(c) == null){
            return false;
        }
        return match(node.next.get(c), word, index+1);
    }
}


class MapSum{

    private Node root;

    private class Node{
        private Integer value;
        private TreeMap<Character, Node> next;

        public Node(Integer value){
            this.value = value;
        }

        public Node(){
            this(0);
        }
    }

    public MapSum(){
        root = new Node();
    }

    public void add(String word, Integer value){
        Node cur = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(root.next.get(c) == null){
                root.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = value;
    }

    public Integer sum(String prefix){
        Node cur = root;
        for(int i=0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private Integer sum(Node node){
        Integer result = node.value;
        for(char c : node.next.keySet()){
            result += sum(node.next.get(c));
        }
        return result;
    }
}
