package com.github.shashi.misc;

import java.util.*;

public class TreeTest {
    public static void main(String[] args) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        BTree.Node node = new BTree.Node(1);
        BTree.Node node2 = new BTree.Node(2);
        BTree.Node node3 = new BTree.Node(3);
        BTree.Node node4 = new BTree.Node(4);
        BTree.Node node5 = new BTree.Node(5);
        node.left = node2;
        node2.left = node3;
        node3.left = node4;
        node4.right = node5;
        BTree ts = new BTree();
        ts.postOrderTraverse(node);

        Queue<Integer> queu  = new LinkedList<>();


    }


private int count = 0;


      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public int getUniTree(BTree.Node root){
        is_uni(root,0);
        return count;
    }
    boolean is_uni(BTree.Node root,int val){
        if(root == null)
            return true;
        if(!is_uni(root.left, root.data) | !is_uni(root.right, root.data))
            return false;
        count++;
        return root.data == val;
    }

    public static int depthOfTree(BTree.Node root){
        if(root == null)
            return 0;
        else{
            int lDepth = depthOfTree(root.left);
            int rDepth = depthOfTree(root.right);
            if(lDepth > rDepth)
                return lDepth +1;
            else return rDepth +1;
        }
    }

    public static class BTree{
        Node root;
        int postCount;
        BTree(int data){
            this.root = new Node(data);
        }
        BTree(){
            this.root = null;
        }


        public static class Node{
            int data;
            Node left, right;
            Node(int data){
                this.data = data;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "data=" + data +
                        ", left=" + left +
                        ", right=" + right +
                        '}';
            }
        }

        public void insert(int data){
            insert(this.root,data);
        }

        public List<Integer> inOrder(){
            return inOrder(new LinkedList<Integer>(),this.root);
        }

        public int getRootIndex(int[] in, int element, int n){
            int index = -1;
            for(int i = 0; i < n; i++){
                if(in[i] == element){
                    index = i;
                    break;
                }
            }
            return index;
        }

        private int getIndex(int[] array,int start,int end, int element){
            int index = -1;
            for(int i = start; i < end; i++){
                if(array[i] == element){
                    index = i;
                    break;
                }

            }
            return index;
        }

        public Node getTree(int[] in, int[] post, int inSt, int inEnd,
                                int pSt, int pEnd){
            if(inSt > inEnd)
                return null;
            int val = post[pEnd];
            Node root = new Node(val);
            if(inSt == inEnd)
                return root;
            int rIndex = getIndex(in,inSt,inEnd,val);
            root.left = getTree(
                    in,post,inSt,rIndex-1,pSt,pSt - inSt+rIndex-1);
            root.right = getTree(
                    in,post,rIndex+1,inEnd,rIndex,pEnd -1);
            return root;
        }

        public Node helper(int[] in, int[] post,int start,int end, int postIndex){
            if(start > end)
                return null;
            int rootIndex = getRootIndex(in,post[postCount],in.length);
            Node root = new Node(post[postCount]);
            postCount--;
            postIndex--;
            root.right = helper(in,post,rootIndex+1,end,postIndex);
            root.left = helper(in,post,start,rootIndex - 1,postIndex);
            return root;
        }

        public Node getTree(int[] in, int[] post, int len){
            int rootIndex = getRootIndex(in,post[len-1],len);
            if(len == 0)
                return null;
            Node root = new Node(post[len-1]);
            if(rootIndex != 0) {
                root.left = getTree(Arrays.copyOfRange(in, 0, rootIndex),
                        Arrays.copyOfRange(post, 0, rootIndex),
                        rootIndex);
            }
            if(len>1){
                root.right = getTree(Arrays.copyOfRange(in, rootIndex + 1, len),
                        Arrays.copyOfRange(post, rootIndex, len-1),
                        len - rootIndex - 1);
            }
            return root;
        }

        public void printPreOrderGivenPostAndInOrder(int[] in, int[] post, int len){
            int rootIndex = getRootIndex(in,post[len-1],len);
            if(len == 0)
                return;

            if(rootIndex != 0){
                System.out.print(post[len - 1]+" ");
                printPreOrderGivenPostAndInOrder(
                        Arrays.copyOfRange(in,0,rootIndex),
                        Arrays.copyOfRange(post,0,rootIndex),
                        rootIndex
                );
                printPreOrderGivenPostAndInOrder(
                        Arrays.copyOfRange(in,rootIndex+1,len),
                        Arrays.copyOfRange(post,rootIndex+1,len),
                        len - rootIndex - 1
                );

            }
        }

        public void printPostOrderGivenPreAndIn(int[] in, int[] pre,int len){
            int rootIndex = getRootIndex(in,pre[0],len);
            if(rootIndex != 0)
                printPostOrderGivenPreAndIn(
                        Arrays.copyOfRange(in,0,rootIndex),
                        Arrays.copyOfRange(pre,1,rootIndex+1),rootIndex);
            if(rootIndex != len -1)
                printPostOrderGivenPreAndIn(
                        Arrays.copyOfRange(in,rootIndex+1,len),
                        Arrays.copyOfRange(pre,rootIndex+1,len),
                        len - rootIndex - 1
                );
            System.out.print(pre[0]+" ");
        }

        public void printPostOrderGivenPreAndInOrder(int[] in, int[] pre, int len){
            int rootIndex  = getRootIndex(in,pre[0],len);
            if(rootIndex != 0)
                printPostOrderGivenPreAndInOrder(in,Arrays.copyOfRange(pre,1,len),rootIndex);
            if(rootIndex != len -1)
                printPostOrderGivenPreAndInOrder(
                        Arrays.copyOfRange(in,rootIndex+1,len),
                        Arrays.copyOfRange(pre,1+rootIndex,len),len - rootIndex-1);

            System.out.println(pre[0]+" ");

        }

        public void inOrderTraverse(Node root){
            if(root == null)
                return;
            else {
                inOrderTraverse(root.left);
                System.out.println(root.data);
                inOrderTraverse(root.right);
            }
        }
        public void preOrderTraverse(Node root){
            if(root == null)
                return;
            else{
                System.out.println(root.data);
                preOrderTraverse(root.left);
                preOrderTraverse(root.right);
            }
        }

        public void inOrderMoris(Node root){
            Node current = root;
            while(current != null){
                if(current.left == null){
                    System.out.print(current.data+" ");
                    current = current.right;
                }
                else{
                    Node predesessor = current.left;
                    while (predesessor.right != current && predesessor.right != null){
                        predesessor = predesessor.right;
                    }
                    if(predesessor.right == null){
                        predesessor.right = current;
                        current = current.left;
                    }
                    else {
                        predesessor.right = null;
                        System.out.print(current.data+" ");
                        current = current.right;
                    }
                }
            }
        }

        public void preOrderNoRecMoris(Node root){
            if(root == null)
                return;
            if(root.left == null && root.right == null){
                System.out.println(root.data);
            }
            Node current = root;
            while (current != null){
                if(current.left == null){
                    System.out.print(current.data+" ");
                    current = current.right;
                }else{
                    Node predecessor = current.left;
                    while (predecessor.right != null && predecessor.right != current){
                        predecessor = predecessor.right;
                    }

                    if(predecessor.right == null){
                        predecessor.right = current;
                        System.out.print(current.data+" ");
                        current = current.left;
                    }else{
                        predecessor.right = null;
                        current = current.right;
                    }
                }
            }
        }

        public void inOrderNoRecMoris(Node root){
            if(root == null)
                return;
            if(root.left == null && root.right == null){
                System.out.println(root.data);
            }
            Node current = root;
            while (current != null){
                if(current.left == null){
                    System.out.print(current.data+" ");
                    current = current.right;
                }else{
                    Node predecessor = current.left;
                    while (predecessor.right != null && predecessor.right != current){
                        predecessor = predecessor.right;
                    }

                    if(predecessor.right == null){
                        predecessor.right = current;
                        current = current.left;
                    }else{
                        System.out.print(current.data+" ");
                        predecessor.right = null;
                        current = current.right;
                    }
                }
            }
        }

        public void postOrderTraverse(Node root){
            if(root == null)
                return;
            else{
                postOrderTraverse(root.left);
                postOrderTraverse(root.right);
                System.out.println(root.data);
            }
        }

        private List<Integer> inOrder(List<Integer> list, Node node){
            if(node == null)
                return list;
            else{
                list.add(node.data);
                inOrder(list,node.left);
                System.out.print(node.data+" ");
                inOrder(list,node.right);
            }
            return list;
        }



        public void delete(int data){

            if(this.root == null)
                return;
            if(this.root.left == null && this.root.right == null){
                if(this.root.data == data){
                    this.root = null;
                    return;
                }else return;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(this.root);
            Node lastNode = null, keyNode = null, prevNode = null;
            while (!queue.isEmpty()){
                lastNode = queue.remove();
                if(lastNode.data == data)
                    keyNode = lastNode;
                if(lastNode.left != null || lastNode.right != null) prevNode = lastNode;
                if(lastNode.left != null)
                    queue.add(lastNode.left);
                if(lastNode.right != null)
                    queue.add(lastNode.right);
            }
            if(keyNode != null){
                int lastNodeValue = lastNode.data;
                if(prevNode != null)
                    prevNode.right = null;
                keyNode.data = lastNodeValue;
            }

        }

        private void deleteLastNode(Node rootNode, Node nodeToDel){
            if(rootNode == null)
                return;
            Queue<Node> queue = new LinkedList<>();
            queue.add(rootNode);
            Node nodeToRemove ;
            while (!queue.isEmpty()){
                nodeToRemove = queue.remove();
                if(nodeToRemove == nodeToDel){
                    nodeToRemove = null;
                    return;
                }
                if(nodeToRemove.left != null){
                    if(nodeToRemove.left == nodeToDel){
                        nodeToRemove.left = null;
                        return;
                    }else queue.add(nodeToRemove.left);
                }
                if(nodeToRemove.right != null){
                    if(nodeToRemove.right == nodeToDel){
                        nodeToRemove.right = null;
                        return;
                    }else queue.add(nodeToRemove.right);
                }

            }
        }

        private void insert(Node rootNode, int data){
            if(rootNode == null){
                rootNode = new Node(data);
            }else{
                Queue<Node> queue = new LinkedList<>();
                queue.add(rootNode);
                while (!queue.isEmpty()){
                    Node node = queue.remove();
                    if(node.left == null){
                        node.left = new Node(data);
                        break;
                    }
                    else
                        queue.add(node.left);
                    if(node.right == null) {
                        node.right = new Node(data);
                        break;
                    }
                    else
                        queue.add(node.right);
                }
            }
        }

    }
}
