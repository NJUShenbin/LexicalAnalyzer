package lex.regex.dfaBuilder;

import lex.regex.fa.FA;
import lex.regex.fa.State;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2016/10/29.
 */
public class EpsnClosureComputerTest {

    DFABuilder dfaBuilder = new DFABuilder();

    FA fa = new FA();
    State s0 = fa.getStartState();
    State s1 = new State();
    State s2 = new State();
    State s3 = new State();
    State s4 = new State();
    State s5 = new State();

    EpsnClosureComputer closureComputer = new EpsnClosureComputer();

    @Before
    public void before(){

        int a = Integer.valueOf('a');

        s4.setAccept(true);
        fa.getStartState().addEdge(null,s1);
        s1.addEdge(a,s2);
        s2.addEdge(null,s3);
        s2.addEdge(null,s4);
        s3.addEdge(a,s5);
        s5.addEdge(null,s3);
        s5.addEdge(null,s4);

    }


    @Test
    public void calculateEpsnClosure() throws Exception {
        fa.resetIncreasedId();

        Set<State> set = closureComputer.calculateEpsnClosure(fa.getStartState());
//        set.forEach(state-> System.out.println(state.getName()));

        set = closureComputer.calculateEpsnClosure(s2);
        set.forEach(state-> System.out.println(state.getName()));

    }

    @Test
    public void testEdgeMap(){

        System.out.println(dfaBuilder.computeEdgeMap(Arrays.asList(s0,s1)));
    }

}