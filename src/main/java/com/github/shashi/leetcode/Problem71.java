package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem71 {
    /*
    Simplify Path
    Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style
    file system, convert it to the simplified canonical path.

    In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the
    directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
    For this problem, any other format of periods such as '...' are treated as file/directory names.

    The canonical path should have the following format:

    The path starts with a single slash '/'.
    Any two directories are separated by a single slash '/'.
    The path does not end with a trailing '/'.
    The path only contains the directories on the path from the root directory to the target file or directory
    (i.e., no period '.' or double period '..')
    Return the simplified canonical path.
    Example 1:

    Input: path = "/home/"
    Output: "/home"
    Explanation: Note that there is no trailing slash after the last directory name.
    Example 2:
    Input: path = "/../"
    Output: "/"
    Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
    Example 3:
    Input: path = "/home//foo/"
    Output: "/home/foo"
    Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

    Constraints:
    1 <= path.length <= 3000
    path consists of English letters, digits, period '.', slash '/' or '_'.
    path is a valid absolute Unix path.

    Approach 1:
    * intuition is to split the given string by / and keep appending the valid names to stack and pop if
    the current string is .. at the end form the string by considering the names from stack
    algo:
    * create the stack of strings, split the input string on slash
    * iterate the string array, if string is empty for single dot then continue
    * if string is 2 dots then if stack is not empty then pop it else skip it
    * else add the string to stack
    * at the end create the string builder iterate over the string array and concat / and string
    * if sb is not empty then return it else return /
     */

    public String simplifyPath(String path) {
        return simplifyPathA1(path);
    }
    public String simplifyPathA1(String path) {
        String[] strings = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String s : strings){
            if(s.equals(".")|| s.isEmpty())continue;
            else if(s.equals("..")){
                if(!stack.isEmpty())stack.pop();
            }else stack.push(s);
        }
        StringBuilder sb = new StringBuilder();
        for(String s : stack){
            sb.append("/");
            sb.append(s);
        }
        return sb.length()>0?sb.toString():"/";
    }
}
