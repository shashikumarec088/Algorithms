package com.github.shashi.misc;/* IMPORTANT: Multiple classes and nested static classes are supported */


//uncomment this if you want to read input.
//imports for BufferedReader

import java.io.BufferedReader;
import java.io.InputStreamReader;
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class Main {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numColleagues = Integer.parseInt(br.readLine());
        if(numColleagues > 0 && numColleagues <= Math.pow(10,5)){
            HashMap map = new HashMap(numColleagues);
            for(int i = 0 ; i < numColleagues; i++){
                String [] numAndName = br.readLine().split(" ");
                if(numAndName.length == 2){
                    int rollNumber = Integer.parseInt(numAndName[0]);
                    String name = numAndName[1];
                    if(rollNumber > 0 && rollNumber <= Math.pow(10,9) &&
                            (name.length() > 0 && name.length() < 26)){
                        map.putData(rollNumber, name);
                    }
                }
            }

            int q = Integer.parseInt(br.readLine());
            if(q > 0 && q <= Math.pow(10,4)){
                for(int i = 0 ; i < q; i++){
                    int x = Integer.parseInt(br.readLine());
                    if(x > 0 && x <= Math.pow(10,9)){
                        System.out.println(map.getVal(x));
                    }
                }

            }
        }


    }

    public static class HashMap{
        private Node [] nameDetails;
        HashMap(int size){
            nameDetails = new Node[size];
        }

        private int getHash(int rollNumber){
            return rollNumber % nameDetails.length;
        }

        public void putData(int rollNumber, String name){
            int hash = getHash(rollNumber);
            Node details = nameDetails[hash];
            Node firstNode = nameDetails[hash];
            Node prevNode = null;
            boolean nodeFound = false;
            Data data = new Data(rollNumber, name);
            while(details != null){
                Data data1 = details.getData();
                if(rollNumber == data1.getId()){
                    details.setData(data);
                    nodeFound = true;
                    break;
                }
                prevNode = details;
                details = details.getNext();
            }
            Node newNode = new Node(data,null);
            if(firstNode == null){
                nameDetails[hash] = newNode;
            }else if(!nodeFound && prevNode != null){
                prevNode.setNext(newNode);
            }
        }

        public String getVal(int rollNumber){
            int hash = getHash(rollNumber);
            Node details = nameDetails[hash];
            String name = "";
            while(details != null){
                Data data = details.getData();
                if(rollNumber == data.getId()){
                    name = data.getName();
                    break;
                }
                details = details.getNext();
            }
            return name;
        }

    }


    public static class Node{
        private Data data;
        private Node next;
        Node(Data data,Node next){
            this.data = data;
            this.next = next;
        }

        public boolean hasNext(){
            return next != null;
        }

        public Data getData(){
            return this.data;
        }

        public void setData(Data data){
            this.data = data;
        }

        public void setNext(Node node){
            this.next = node;
        }

        public Node getNext(){
            return this.next;
        }
    }

    public static class Data{
        private int id;
        private String name;
        Data(int id, String name){
            this.id = id;
            this.name = name;
        }

        public int getId(){
            return this.id;
        }

        public String getName(){
            return this.name;
        }
    }
}
