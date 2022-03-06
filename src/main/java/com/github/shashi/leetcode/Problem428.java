package com.github.shashi.leetcode;
import java.util.*;
public class Problem428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    // Encodes a tree to a single string.

    public String serialize(Node root) {
        return serializeA3(root);
    }

    public Node deserialize(String data) {
        return deserializeA3(data);
    }

    public String serializeA3(Node root){
        if(root==null)return "";
        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node endNode = new Node(-1);
        Node childNode = new Node(-2);
        queue.add(endNode);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(node==endNode){
                sb.append("#");
                if(!queue.isEmpty())
                    queue.add(endNode);
            }
            else if(node==childNode)
                sb.append("$");
            else{
                sb.append((char)(node.val+'0'));
                for(Node child: node.children)
                    queue.add(child);
                if(queue.peek()!=endNode)
                    queue.add(childNode);
            }
        }
        return sb.toString();
    }

    public Node deserializeA3(String data){
        if(data.isEmpty())return null;
        Node root = new Node(data.charAt(0)-'0',new ArrayList<>());
        LinkedList<Node> curLevel = new LinkedList<>();
        LinkedList<Node> prevLevel = new LinkedList<>();
        curLevel.add(root);
        Node parent = root;
        for(int i=1; i<data.length();i++){
            char ch = data.charAt(i);
            if(ch=='#'){
                prevLevel = curLevel;
                curLevel = new LinkedList<>();
                if(!prevLevel.isEmpty())
                    parent=prevLevel.poll();
            }
            else if(ch=='$')
                parent = prevLevel.poll();
            else{
                Node node = new Node(ch-'0',new ArrayList<>());
                curLevel.add(node);
                parent.children.add(node);
            }
        }
        return root;
    }

    public Node deserializeA2(String data){
        return deseralizeA2Rec(data,new int[]{0});
    }

    public Node deseralizeA2Rec(String s, int[] index){
        if(s.length()==0)return null;
        if(index[0]==s.length())return null;
        int val = s.charAt(index[0])-'0';
        index[0]++;
        int len = s.charAt(index[0])-'0';
        Node node = new Node(val,new ArrayList<>());
        for(int i=0; i<len;i++){
            index[0]++;
            node.children.add(deseralizeA2Rec(s,index));
        }
        return node;
    }

    public String serializeA2(Node root) {
        return serializeA2Rec(root,new StringBuilder()).toString();
    }

    public StringBuilder serializeA2Rec(Node root, StringBuilder sb){
        if(root==null)return sb;
        sb.append((char)(root.val+'0'));
        sb.append((char)(root.children.size()+'0'));
        for(Node child: root.children)
            serializeA2Rec(child,sb);
        return sb;
    }
    public String serializeA1(Node root) {
        return encodeRec(root, new int[]{1},new StringBuilder(),null).toString();
    }

    public StringBuilder encodeRec(Node root, int[] counter,StringBuilder sb,
                                   Integer parentId){
        if(root==null)return sb;
        sb.append((char)(counter[0]+'0'));
        sb.append((char)(root.val+'0'));
        sb.append((char)(parentId==null?'N':parentId+'0'));
        Integer pId = counter[0];
        for(Node child: root.children){
            counter[0]++;
            encodeRec(child,counter,sb,pId);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public Node deserializeA1(String data) {
        if(data.length()==0)return null;
        Map<Integer,Node> map = new HashMap<>();
        for(int i=0; i<data.length(); i+=3){
            int id = data.charAt(i)-'0';
            int val = data.charAt(i+1)-'0';
            map.put(id,new Node(val,new ArrayList<>()));
        }

        for(int i=3; i<data.length(); i+=3){
            int id = data.charAt(i)-'0';
            int pid = data.charAt(i+2)-'0';
            Node node = map.get(id);
            Node pNode = map.get(pid);
            pNode.children.add(node);
        }
        return map.get(data.charAt(0)-'0');
    }
}