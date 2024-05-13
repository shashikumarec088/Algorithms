package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Problem380 {
    /*
    Insert Delete GetRandom O(1)
    Implement the RandomizedSet class:

    RandomizedSet() Initializes the RandomizedSet object.
    bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present,
    false otherwise.
    bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
    int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least
    one element exists when this method is called). Each element must have the same probability of being returned.
    You must implement the functions of the class such that each function works in average O(1) time complexity.

    Example 1:
    Input
    ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
    [[], [1], [2], [2], [], [1], [2], []]
    Output
    [null, true, false, true, 2, true, false, 2]
    Explanation
    RandomizedSet randomizedSet = new RandomizedSet();
    randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
    randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
    randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
    randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
    randomizedSet.insert(2); // 2 was already in the set, so return false.
    randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.

    Constraints:
    -231 <= val <= 231 - 1
    At most 2 * 105 calls will be made to insert, remove, and getRandom.
    There will be at least one element in the data structure when getRandom is called.

    Approach 1:
    * intuition is that we can get the insertion deletion using the hashmap but inorder to get the random element
    in constant time we need to maintain the elements in arrayList, but to support the removal of element from list
    is o(n) time if we do not know the index, if we know the index then we can do it in constant time by swaping the
    last element to the index and deleting the last element always as this is o(1) time, so we need to maintain the
    index in the map for each element so that we can get the index for any given element. We can get the random integer
    by using random.nextInt in java.

    algo:
    * create a hashmap to store the val and index, arraylist to store element for getting random values and random
    * during insert if element is present then return false, else put element in map with list size as index and
    add element to end of list.
    * during remove if val is not present then return false else get the element index from the map, swap the last element
    to index in list and update the last element index to current element index in map and remove the last element from list
    and remove the element from the map
    * to get random in the list get the element by getting position using rand.nextInt(list.size())
    *

     */
    Map<Integer, Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random();

    public Problem380() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val))return false;
        map.put(val,list.size());
        list.add(list.size(),val);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val))return false;
        int index = map.get(val);
        map.put(list.get(list.size()-1),index);
        list.set(index,list.get(list.size()-1));
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}