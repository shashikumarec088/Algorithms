package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem909 {
    /*
    Snakes and Ladders
    You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style
    starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
    You start on square 1 of the board. In each move, starting from square curr, do the following:
    Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
    This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations,
    regardless of the size of the board.
    If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
    The game ends when you reach the square n2.
    A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake
    or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
    Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the
    start of another snake or ladder, you do not follow the subsequent snake or ladder.
    For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow
    the ladder to square 3, but do not follow the subsequent ladder to 4.
    Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.

    Example 1:
    Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],
    [-1,15,-1,-1,-1,-1]]
    Output: 4
    Explanation:
    In the beginning, you start at square 1 (at row 5, column 0).
    You decide to move to square 2 and must take the ladder to square 15.
    You then decide to move to square 17 and must take the snake to square 13.
    You then decide to move to square 14 and must take the ladder to square 35.
    You then decide to move to square 36, ending the game.
    This is the lowest possible number of moves to reach the last square, so return 4.
    Example 2:
    Input: board = [[-1,-1],[-1,3]]
    Output: 1
    Constraints:

    n == board.length == board[i].length
    2 <= n <= 20
    board[i][j] is either -1 or in the range [1, n2].
    The squares labeled 1 and n2 do not have any ladders or snakes.

    Approach 1: bfs algo
    * intuition is by looking the board we can think of it as a graph where each cell can be represented as a node
    and there are edges between cells within 6 and snake and ladder as new edges, since problem asks us to find
    minimum number of moves, which indicates that it is a shortest path problem, for unweighted graphs the shorted
    path can be found using bfs algo. where we can push the first cell to queue and keep finding its neighbors and
    pushing those to queue if those are not visited and capturing the moves needed to the neighbor as moves needed
    for reaching the cur position + 1.
    algo:
    * we need to create the cells position for each cell from 1 to n2, to do this we start from bottom left and
    traverse the board alternatively from left to right and right to left,this can be done by checking if we are
    at odd row or even row if add row then we go from left to right, if we are at event row we go from right to
    left.
    * create an cells array of type Pair where Pair is a class which has 2 methods getKey and getValue which provides
    row and column values respectively, cells array size we consider n2+1 so that we can access the cell positions
    indexed from 1
    * initialize cell=1, flag=true, iterate r from n-1 to 0 and inside if flag is true then iterate column from 0 to n
    else from n-1 to 0, at each set cells[cell] as new Pair(r,c) after each row make flag=!flag
    * create an array moves which stores the number of moves needed to reach the cell position, it is of type integer
    and size is n2+1 so that we can access the cell moves indexed from 1 to n2
    * set the default value for all positions in moves to -1 which indicates that the cell is not reached yet
    * set the moves[1] to 0 indicating we no need to move to reach to 1st cell since it is the starting cell
    * create a queue of type integer and add 1 to queue and iterate until queue is empty
    * for each iteration get the cur cell and iterate for next cells we can reach from cur cell that is
    next=cur+1 to min(n2,cur+6)
    * for each next position get the r and c values from the cells array ie r=cells[next].getKey() and c=cells[next].
    getValue(), check the value at this position in the board if it is -1 then destination we can reach is next only
    if it is not then destination will be the value at that position in the board
    * check moves at the destination index if it -1 then it indicates that still this cell is not reached then update
    the moves needed to reach destination to moves needed to reach cur position + 1, ie moves[destination]=moves[cur]+1
    and also add the destination to queue since it is not reached before so we need to consider the neighbor cells for
    this position
    * once all the cell positions are visited then moves at n2 will hold the minimum moves needed to reach n2, it holds
    minimum value because we are doing bfs and we update the moves on the first reach of the position which will be with
    minimum moves always
    time & space:
    * it takes n2 time and n2 space

    Approach 2: bfs with dijkstra algorithm to consider then next node to poll
    * intuition is when considering the next neighbor to traverse in the queue we can consider the node with the
    shortest distance.
    algo:
    * most of the algo is same, instead of queue of ints we use priorityQueue of type int array which will have 2
    elements 0th will be distance and 1st will the actual cell, we add the custom coparator which compares on
    distances
    * we poll the element from the queue untill queue is empty and if dist at moves[cur] < cur dist then skip
    as the dist at moves[cur] is updated with lesser value we do not need to consider the higher distance possibilities
    * while checking the the moves at destination we update the moves if moves at destination is -1 or > moves[cur]+1
    and also we add the destination to the queue
     */

    public int snakesAndLadders(int[][] board) {
        return snakesAndLaddersA1(board);
    }

    class Pair{
        int key;
        int value;
        Pair(int key, int value){
            this.key=key;
            this.value=value;
        }

        int getKey(){
            return key;
        }

        int getValue(){
            return value;
        }
    }

    public int snakesAndLaddersA1(int[][] board) {
        int n = board.length;
        Pair[] cells = new Pair[n*n+1];
        int cell=1;
        boolean flag=true;
        for(int r=n-1; r>=0;r--){
            if(flag){
                for(int c=0;c<n;c++)
                    cells[cell++]=new Pair(r,c);
            }else{
                for(int c=n-1; c>=0;c--)
                    cells[cell++]=new Pair(r,c);
            }
            flag=!flag;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        int[] moves = new int[n*n+1];
        for(int i=0; i<moves.length;i++)
            moves[i]=-1;
        moves[1]=0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int next=cur+1; next<=Math.min(cur+6,n*n);next++){
                int r=cells[next].getKey(),c=cells[next].getValue();
                int dest = board[r][c]!=-1?board[r][c]:next;
                if(moves[dest]==-1){
                    queue.offer(dest);
                    moves[dest]=moves[cur]+1;
                }
            }
        }
        return moves[n*n];
    }

    public int snakesAndLaddersA2(int[][] board) {
        int n = board.length;
        Pair[] cells = new Pair[n*n+1];
        int cell=1;
        boolean flag=true;
        for(int r=n-1; r>=0;r--){
            if(flag){
                for(int c=0;c<n;c++)
                    cells[cell++]=new Pair(r,c);
            }else{
                for(int c=n-1; c>=0;c--)
                    cells[cell++]=new Pair(r,c);
            }
            flag=!flag;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)
                -> a[0]-b[0]);
        queue.add(new int[]{0,1});
        int[] moves = new int[n*n+1];
        for(int i=0; i<moves.length;i++)
            moves[i]=-1;
        moves[1]=0;
        while(!queue.isEmpty()){
            int[] distCell = queue.remove();
            int cur = distCell[1];
            for(int next=cur+1; next<=Math.min(cur+6,n*n);next++){
                int r=cells[next].getKey(),c=cells[next].getValue();
                int dest = board[r][c]!=-1?board[r][c]:next;
                if(moves[dest]==-1){
                    moves[dest]=moves[cur]+1;
                    queue.add(new int[]{moves[dest],dest});
                }
            }
        }
        return moves[n*n];
    }
}
