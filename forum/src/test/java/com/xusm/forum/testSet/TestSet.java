package com.xusm.forum.testSet;

import java.util.HashSet;

public class TestSet {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(5);
        set.add(4);
        set.add(1);
        System.out.println(set);
    }
}
