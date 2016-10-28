package lex.regex.fa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/10/24.
 */
public class FATest {

    FA fa = new FA();

    @Before
    public void before(){
//        State start = fa.getStartState();
//        State s1 = fa.createState();
//        State s2 = fa.createState();
//        State s3 = fa.createState();
//        State s4 = fa.createState();
//
//        start.addEdge(1,s1);
//        start.addEdge(2,s2);
//        start.addEdge(null,s3);
//        s1.addEdge(2,s2);
//        s3.addEdge(3,s1);
//        s2.addEdge(3,s2);
//        s2.addEdge(null,start);
//        s2.addEdge(4,s3);
//        s3.addEdge(3,start);

    }

    @Test
    public void testSetId(){
        State start = fa.getStartState();
        State s1 = new State("aa");
        State s2 = new State("bb");
        State s3 = new State("cc");
        State s4 = new State("dd");

        start.addEdge(1,s1);
        start.addEdge(2,s2);
        start.addEdge(null,s3);
        s1.addEdge(2,s2);
        s3.addEdge(3,s1);
        s2.addEdge(3,s2);
        s2.addEdge(null,start);
        s2.addEdge(4,s3);
        s3.addEdge(3,start);

        fa.print();

        fa.resetIncreasedId();
        fa.print();

    }

}