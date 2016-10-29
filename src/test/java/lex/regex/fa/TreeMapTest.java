package lex.regex.fa;

import org.junit.Test;

import java.util.TreeMap;

/**
 * Created by sbin on 2016/10/29.
 */
public class TreeMapTest {

    @Test
    public void treeMapTest(){
        TreeMap<Integer,Integer> treeMap1 = new TreeMap<>();
        TreeMap<Integer,Integer> treeMap2 = new TreeMap<>();

        treeMap1.put(2,2);
        treeMap1.put(1,1);
        treeMap1.put(3,3);

        treeMap2.put(1,1);
        treeMap2.put(2,2);
        treeMap2.put(3,3);

        assert treeMap1.hashCode() == treeMap2.hashCode();
    }

}
