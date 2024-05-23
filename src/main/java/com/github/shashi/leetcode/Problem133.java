package com.github.shashi.leetcode;

import java.util.*;

public class Problem133 {
    /*
    Clone Graph
    Given a reference of a node in a connected undirected graph.
    Return a deep copy (clone) of the graph.
    Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
    class Node {
        public int val;
        public List<Node> neighbors;
    }

    Example 1:
    Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
    Output: [[2,4],[1,3],[2,4],[1,3]]
    Explanation: There are 4 nodes in the graph.
    1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
    2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
    3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
    4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
    Example 2:
    Input: adjList = [[]]
    Output: [[]]
    Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it
    does not have any neighbors.
    Example 3:
    Input: adjList = []
    Output: []
    Explanation: This an empty graph, it does not have any nodes.

    Constraints:
    The number of nodes in the graph is in the range [0, 100].
    1 <= Node.val <= 100
    Node.val is unique for each node.
    There are no repeated edges and no self-loops in the graph.
    The Graph is connected and all nodes can be visited starting from the given node.

    Approach 1: dfs recursion
    * intuition is to do the normal dfs with visited map which stores mapping between the old node and new node
    for each neighbor call the dfs
    algo:
    * call recursion with root, empty map of node,node which returns node
    * base case is to check if root is null if so return null else if node in map if so return map.get(root)
    * create a new node with root.val
    * iterate over the neighbors of root and add the new nodes to cur new node neighbors by calling rec on each neighbor
    * return new node at the end
    * from the main method return the node returned by recursion
    time & space:
    * takes n+m (nodes plus edges) time and n space for storing nodes in map as well as recursion stack

    Approach 2: bfs using queue
    * intuition is to do normal bfs using queue along with visited map, if any node is not visited we create a node
    add to queue and also to map
    algo:
    * if root is null then return null, create a queue of nodes and also map of node, node
    * add root to queue and also to map with new node as value
    * iterate until queue is empty, get the node from queue, iterate over its neighbors
    * check if neighbor in map if not add to map with new node created
    * get the cur nodes new node from map and add new neighbor by getting value from map
    * return map.get(root) at the end
    time & space:
    * it takes n+m time and n space for queue.
     */
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
    public Node cloneGraph(Node node) {
        return cloneGraphA2(node);
    }

    public Node cloneGraphA1(Node node) {
        return rec(node,new HashMap<Node,Node>());
    }

    public Node cloneGraphA2(Node node) {
        if(node==null)return null;
        Map<Node,Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();;
            for(Node nei : cur.neighbors){
                if(!map.containsKey(nei)){
                    map.put(nei,new Node(nei.val));
                    queue.offer(nei);
                }
                map.get(cur).neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }

    public Node rec(Node node, Map<Node,Node> map){
        if(node==null)return null;
        if(map.containsKey(node))return map.get(node);
        Node cur = new Node(node.val);
        map.put(node,cur);
        for(Node nei:node.neighbors)
            cur.neighbors.add(rec(nei,map));
        return cur;
    }
}