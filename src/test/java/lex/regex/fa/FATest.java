package lex.regex.fa;

import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/10/24.
 */
public class FATest {

    FA fa = new FA();

    @Before
    public void before(){
        State start = fa.getStartState();
        State s1 = fa.createState();
        State s2 = fa.createState();
        State s3 = fa.createState();
        State s4 = fa.createState();

        start.addEdge(1,s1);
        start.addEdge(2,s2);
        start.addEdge(null,s3);
        s1.addEdge(2,s2);
        s3.addEdge(3,s1);
        s2.addEdge(3,s2);
        s2.addEdge(null,start);
        s2.addEdge(4,s3);
        s3.addEdge(3,start);

    }

    @org.junit.Test
    public void transform() throws Exception {

    }

    @org.junit.Test
    public void getStartState() throws Exception {

    }

    @org.junit.Test
    public void forEach() throws Exception {
        fa.print();
    }

    @org.junit.Test
    public void reset() throws Exception {

    }

}