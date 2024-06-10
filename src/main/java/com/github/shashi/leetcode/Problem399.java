package com.github.shashi.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem399 {
    /*
    Evaluate Division
    You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
    and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
    You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
    for Cj / Dj = ?.
    Return the answers to all queries. If a single answer cannot be determined, return -1.0.
    Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and
    that there is no contradiction.
    Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

    Example 1:
    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
    Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
    Explanation:
    Given: a / b = 2.0, b / c = 3.0
    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
    return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
    note: x is undefined => -1.0
    Example 2:
    Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],
    ["cd","bc"]]
    Output: [3.75000,0.40000,5.00000,0.20000]
    Example 3:
    Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
    Output: [0.50000,2.00000,-1.00000,-1.00000]
    Constraints:
    1 <= equations.length <= 20
    equations[i].length == 2
    1 <= Ai.length, Bi.length <= 5
    values.length == equations.length
    0.0 < values[i] <= 20.0
    1 <= queries.length <= 20
    queries[i].length == 2
    1 <= Cj.length, Dj.length <= 5
    Ai, Bi, Cj, Dj consist of lower case English letters and digits.

    Approach 1: dfs using recursion
    * inuition is to construct the graph for the relation between the variables with values as weight to travel from
    node to node and then finding the path between the query nodes
    algo:
    * create map of string, map of string,double which holds the specific node and its neighbors along with the value
    needed to travel from node to neighbor.
    * iterate over all the equations, for each equation get the 1st and 2nd node, check if they exist in map if not
    create a new hashmap and put with node as key and hashmap as value
    * add the 2nd node as neighbor for first node and value as weight
    * add the 1st node as neighbor for the 2nd node and 1/value as weight
    * create the result array of type double with size equal to queries
    * iterate over queries, for each query get src and trg
    * check if either is not present if so make res[i] as -1 else
    * check if they are same if so make res[i] as 1
    * else do dfs with graph,src,dest,initial prod=1 and empty hashset to track the visited nodes while traversing
    and assing the value to res[i], initialize val=-1
    * in dfs first we mark the source as visited by adding to visit set
    * we check if trg is neighbor of src if so we compute the result as prod* graph.get(src).get(trg)
    * else we iterate over the neighbors and check if neighbor is not visited if not we do dfs with neighbor
    *after each dfs we check if we found the path if so then val will not be -1 we can break and return the value
    else we continue for next neighbor.
    * point to remember we no need to remove the visited node at the end intuition is if we visited the node before
    then we no need to visit again even from other branches because it will not lead to target any way
    time & space:
     m*n where  n is the nodes in graph and m is the queries(in worst case we will travel each node in graph for each
     query), takes n space to build the graph,(which has n nodes and 2n edges total 3n approx n)

     */

    public static void main(String[] args) {
        Problem399 problem399 = new Problem399();
        String[][] strs = {{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}};
        double[] vals = {3.0,4.0,5.0,6.0};
        String[][] query = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
        List<List<String>> equations = Arrays.stream(strs).map(Arrays::asList).collect(Collectors.toList());
        List<List<String>> queries = Arrays.stream(query).map(Arrays::asList).collect(Collectors.toList());
        problem399.calcEquationA1(equations,vals,queries);

    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        return calcEquationA1(equations,values,queries);
    }

    public double[] calcEquationA1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String,Double>> graph = new HashMap<>();
        for(int i=0; i<equations.size();i++){
            String devidend = equations.get(i).get(0),deviser=equations.get(i).get(1);
            graph.putIfAbsent(devidend,new HashMap<>());
            graph.putIfAbsent(deviser,new HashMap<>());
            graph.get(devidend).put(deviser,values[i]);
            graph.get(deviser).put(devidend,1/values[i]);
        }
        double[] res = new double[queries.size()];
        for(int i=0; i<res.length; i++){
            String devidend = queries.get(i).get(0),deviser=queries.get(i).get(1);
            if(!graph.containsKey(devidend)||!graph.containsKey(deviser))
                res[i]=-1.0;
            else if(devidend==deviser)
                res[i]=1.0;
            else{
                res[i] = dfs(graph,devidend,deviser,1.0,new HashSet<>());
            }
        }
        return res;
    }

    public double dfs(Map<String,Map<String,Double>> graph, String src, String trg,
                      double prod, Set<String> visit){
        if(src.equals(trg))return prod;
        visit.add(src);
        double val = -1.0;
            for(Map.Entry<String,Double> pair : graph.get(src).entrySet()){
                if(!visit.contains(pair.getKey()))
                    val = dfs(graph,pair.getKey(),trg,prod*pair.getValue(),visit);
                if(val !=1.0)break;
            }
        return val;
    }
}
