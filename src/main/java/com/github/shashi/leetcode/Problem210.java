package com.github.shashi.leetcode;
import java.util.*;
public class Problem210 {
    /*
    Course Schedule II
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
    prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take
    course ai.
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any
    of them. If it is impossible to finish all courses, return an empty array.

    Example 1:
    Input: numCourses = 2, prerequisites = [[1,0]]
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
    Example 2:
    Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
    So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
    Example 3:

    Input: numCourses = 1, prerequisites = []
    Output: [0]
    Constraints:
    1 <= numCourses <= 2000
    0 <= prerequisites.length <= numCourses * (numCourses - 1)
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    ai != bi
    All the pairs [ai, bi] are distinct.

    Approach 1: topological sort (kahn's algorithm)
    * if we look that prereqs this problem boils down to topological sort where we first start with node whose indegree
    is 1 and start dec ints neighbors indegree and consider the nodes whose indegree becomes 0, at the end if we visited
    all the nodes then we can finish the course, keep adding all the visited nodes to list to return as result
    if its size is number of courses
    algo:
    * create a indegree array of size numCourses, neighbors array of type List of integers and size numCourses
    * iterate over prereqs inc indegree for pre[0] and add pre[1] as neighbor for pre[0]
    * create queue of type integer, add all indexes with indegree 0, intialize j=0 and also res array of its
    of size numCourses
    * iterate until queue is emtpy inc visit, poll the node from queue, add the node to res[j] and inc j, then
    iterate over all the neighbors of node
    * dec indegree for all neighbors and if indegree for any neighbor becomes 0 then add to queue
    * at the end if j== numCourses return res else return empty array
    time & space:
    * it take m+n space if there are n nodes and m prereqs and also takes m+n space for storing the neighbors
    of all the nodes

    Approach 2: dfs
    * intuition is to build the adj graph from the prereqs and traverse each node and check if cycle exists,
    to check if cycle exists within the path we need to hold extra array to track the elements that we already
    traversed in the path, keep pushing the elements to stack so that we get the courses that needs tobe visited
    in order at the end if no cycles
    algo:
    * create adj list of type list of integers, per each prereq add the neighbors of pre[1] to list
    * create bool arrays for visit and path arrays, visit is needed to avoid the repeatitive traversal
    from different nodes if they pass through same node, path is needed to check if their is a cycle within
    path when we start the traversal from specific node, we reset the values in path to reuse the same
    variable when traversing for other nodes , initialize stack of integer to hold the visited courses
    across the node iterations which will hold the courses tobe visited in order when no cycles exists
    in the graph
    * iterate over all nodes in adj list call dfs with node, adj, visit, path and stack
    * in dfs check if path[i] is set if so return true indicating that cycle exists, Note: first we
    should check the path before visit else it leads wrong result (check for case [0,1],[1,0])
    * next check if visit[i] is set if so return false indicating that this node if already visited and
    no cycle exists
    * set both path and visit
    * iterate over neighbors if i and call dfs, if dfs returns true then return true else keep iterating
    over all neighbors, once all neighbors visiting is completed  reset the path[i] to false so we can
    use it for next path
    * add the x to stack which holds the cources in order tobe visited if no cycles exist
    * return false indicating that no cycle is detected
    * in the main function if dfs returns true indicating cycle exists then return empty array
    else pop all the elements from stack and add those to res array and return it
    time & space:
    * time is m+n where m is prereqs and n is nodes and space is also m+n
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return findOrderA2(numCourses,prerequisites);
    }

    public int[] findOrderA2(int numCourses, int[][] pres) {
        int[] degrees = new int[numCourses];
        int[] res = new int[numCourses];
        int j=0;
        List<Integer>[] neighbors = new List[numCourses];
        for(int i=0; i<numCourses;i++)
            neighbors[i]=new ArrayList<>();
        for(int[] pre : pres){
            neighbors[pre[1]].add(pre[0]);
        }

        boolean[] path = new boolean[numCourses];
        boolean[] visit = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<numCourses; i++)
            if(dfs(i,neighbors,visit,path,stack))return new int[0];
        while(!stack.isEmpty())
            res[j++]=stack.pop();
        return res;
    }

    public boolean dfs(int x,List<Integer>[] neighbors, boolean[] visit,
                       boolean[] path, Stack<Integer> stack){
        if(path[x])return true;
        if(visit[x])return false;
        visit[x]=true;
        path[x]=true;
        for(int nei:neighbors[x])
            if(dfs(nei,neighbors,visit,path,stack))
                return true;
        stack.push(x);
        path[x]=false;
        return false;
    }

    public int[] findOrderA1(int numCourses, int[][] pres) {
        int[] degrees = new int[numCourses];
        int[] res = new int[numCourses];
        int j=0;
        List<Integer>[] neighbors = new List[numCourses];
        for(int i=0; i<numCourses;i++)
            neighbors[i]=new ArrayList<>();
        for(int[] pre : pres){
            neighbors[pre[1]].add(pre[0]);
            degrees[pre[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses;i++)
            if(degrees[i]==0)queue.offer(i);
        while(!queue.isEmpty()){
            int node = queue.poll();
            res[j++]=node;
            for(int nei:neighbors[node]){
                degrees[nei]--;
                if(degrees[nei]==0)
                    queue.offer(nei);
            }
        }
        if(j==numCourses)
            return res;
        else return new int[0];
    }
}
