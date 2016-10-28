package lex.regex.fa;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * Created by sbin on 2016/10/28.
 */
public class PatternOrderTest {

    @Test
    public void testOrder(){
        Set<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(3);
        set.add(4344);
        set.add(12);
        set.add(432424);
        set.forEach(s-> System.out.println(s));
    }

    @Test
    public void testHashSet(){
        State state = new State();
        State state1 = new State();
        HashSet<State> stateHashSet = new HashSet<>();
        stateHashSet.add(state);

        System.out.println(stateHashSet.add(state));
        System.out.println(stateHashSet.add(state1));
    }

    @Test
    public void testArrayHash(){
        State state1 = new State();
        State state2 = new State();
        State state3 = new State();

        List<State> list = Arrays.asList(state1,state2,state3);

        ImmutableList<State> states = ImmutableList.copyOf(list);

        System.out.println(list.hashCode() == states.hashCode());


    }

}
