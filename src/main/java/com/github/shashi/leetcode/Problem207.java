package com.github.shashi.leetcode;
import java.util.*;
public class Problem207 {
    /*
    Course Schedule
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
    prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take
    course ai.
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.

    Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: true
    Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0. So it is possible.
    Example 2:
    Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take.
    To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
    So it is impossible.

    Constraints:
    1 <= numCourses <= 2000
    0 <= prerequisites.length <= 5000
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    All the pairs prerequisites[i] are unique.

    Approach 1: topological sort (kahn's algorithm)
    * if we look that prereqs this problem boils down to topological sort where we first start with node whose indegree
    is 0 and start dec ints neighbors indegree and consider the nodes whose indegree becomes 0, at the end if we visited
    all the nodes then we can finish the course
    algo:
    * create a indegree array of size numCourses, neighbors array of type List of integers and size numCourses
    * iterate over prereqs inc indegree for pre[0] and add pre[0] as neighbor for pre[1]
    * create queue of type integer, add all indexes with indegree 0, intialize visit=0
    * iterate until queue is emtpy inc visit, poll the node from queue, iterate over all the neighbors of node
    * dec indegree for all neighbors and if indegree for any neighbor becomes 0 then add to queue
    * at the end return true if visit==courses else false.
    time & space:
    * it take m+n space if there are n nodes and m prereqs and also takes m+n space for storing the neighbors
    of all the nodes

    Approach 2: dfs
    * intuition is to build the adj graph from the prereqs and traverse each node and check if cycle exists,
    to check if cycle exists within the path we need to hold extra array to track the elements that we already
    traversed in the path
    algo:
    * create adj list of type list of integers, per each prereq add the neighbors of pre[1] to list
    * create bool arrays for visit and path arrays, visit is needed to avoid the repeatitive traversal
    from different nodes if they pass through same node, path is needed to check if their is a cycle within
    path when we start the traversal from specific node, we reset the values in path to reuse the same
    variable when traversing for other nodes
    * iterate over all nodes in adj list call dfs with node, adj, visit and path return false if we get
    true from dfs
    * in dfs check if path[i] is set if so return true indicating that cycle exists, Note: first we
    should check the path before visit else it leads wrong result (check for case [0,1],[1,0])
    * next check if visit[i] is set if so return false indicating that this node if already visited and
    no cycle exists
    * set both path and visit
    * iterate over neighbors if i and call dfs, if dfs returns true then return true else keep iterating
    over all neighbors, once all neighbors visiting is completed  reset the path[i] to false so we can
    use it for next path
    * return false indicating that no cycle is detected
    time & space:
    * time is m+n where m is prereqs and n is nodes and space is also m+n
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return canFinishA2(numCourses,prerequisites);
    }
    boolean[] visitedOuter;

    public boolean canFinishA1(int nc, int[][] pres) {
        int[] in = new int[nc];
        List<Integer>[] neis = new List[nc];
        for(int i=0; i<nc; i++)
            neis[i]=new ArrayList<>();
        for(int[] pre : pres){
            in[pre[0]]++;
            neis[pre[1]].add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<nc;i++)
            if(in[i]==0)queue.offer(i);
        int visit=0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            visit++;
            for(int nei : neis[cur]){
                in[nei]--;
                if(in[nei]==0)
                    queue.offer(nei);
            }
        }
        return visit==nc;
    }

    public boolean canFinishA2(int numCourses, int[][] prerequisites){
        Map<Integer,List<Integer>> adjList = new HashMap<>();
        visitedOuter = new boolean[numCourses];
        for(int[] edge : prerequisites){
            List<Integer> list = adjList.getOrDefault(edge[1],new ArrayList<>());
            list.add(edge[0]);
            adjList.put(edge[1],list);
        }
        boolean[] visited = new boolean[numCourses];
        for(int i=0; i<numCourses; i++)
            if(backTrack(i,adjList,visited))return false;
        return true;
    }

    public boolean backTrack(int i, Map<Integer,List<Integer>> adjList, boolean[] visited){
        if(visitedOuter[i])return false;
        if(visited[i])return true;
        if(!adjList.containsKey(i))return false;
        visited[i]=true;

        boolean ret=false;
        for(Integer nei : adjList.get(i)){
            ret = backTrack(nei,adjList,visited);
            if(ret)break;
        }
        visited[i]=false;
        visitedOuter[i]=true;
        return ret;
    }

    public boolean canFinishA3(int n, int[][] prereq){
        List<Integer>[] adj = new ArrayList[n];
        visitedOuter = new boolean[n];
        for(int i=0; i<n; i++) adj[i] = new ArrayList<>();
        for(int[] edj : prereq)
            adj[edj[0]].add(edj[1]);
        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[n];
            if(dfs(i,adj,visited)) return false;
        }
        return true;
    }

    public boolean dfs(int i, List<Integer>[] adj, boolean[] visited){
        if(visitedOuter[i])return false;
        if(visited[i]) return true;
        visited[i]=true;
        for(int nei : adj[i]){
            if(dfs(nei,adj,visited)) return true;
        }
        visited[i]=false;
        visitedOuter[i]=true;
        return false;
    }
}
