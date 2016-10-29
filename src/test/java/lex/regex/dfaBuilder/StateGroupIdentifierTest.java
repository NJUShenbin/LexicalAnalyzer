package lex.regex.dfaBuilder;

import com.google.common.collect.Sets;
import lex.regex.fa.FA;
import lex.regex.fa.State;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2016/10/29.
 */
public class StateGroupIdentifierTest {

    FA fa = new FA();
    State s0 = fa.getStartState();
    State s1 = new State();
    State s2 = new State();
    State s3 = new State();
    State s4 = new State();
    State s5 = new State();

    @Before
    public void before(){
        fa.resetIncreasedId();
    }

    @Test
    public void testHash(){
        HashSet<State> set1 = Sets.newHashSet(s0,s1,s2,s3,s4,s5);
        HashSet<State> set2 = Sets.newHashSet(set1);

        StateGroupIdentifier identifier1 = new StateGroupIdentifier();
        StateGroupIdentifier identifier2 = new StateGroupIdentifier();
        identifier1.getEdgeGroupMap().put(1,set1);
        identifier2.getEdgeGroupMap().put(1,set2);

        HashSet<StateGroupIdentifier> hashSet = new HashSet<>();
        hashSet.add(identifier1);
        assert hashSet.contains(identifier2);
    }

}