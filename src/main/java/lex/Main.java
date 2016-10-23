package lex;

import lex.lexfile.LexRule;
import lex.mainFlow.Lex;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/**
 * Created by admin on 2016/10/22.
 */
public class Main {

    public static void main(String[] args) {
        new Lex().generate("./myLex.lex");

        DirectedGraph<String,DefaultEdge> g = new
                DefaultDirectedGraph<String, DefaultEdge>
                ((v1,v2) ->{return new DefaultEdge();});

    }
}
