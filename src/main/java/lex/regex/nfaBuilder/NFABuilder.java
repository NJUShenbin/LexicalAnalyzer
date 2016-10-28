package lex.regex.nfaBuilder;

import lex.regex.fa.FA;
import lex.regex.regexProceser.Element;

import java.util.List;
import java.util.Map;

/**
 * 根据 pattern-regex构造NFA
 */
public class NFABuilder {

    public FA build(Map<String,List<Element>> regexElementMap){
        FA root = new FA("nfa");

        regexElementMap.forEach((k,v)->{
            FA subFa = new RegexEvaluater().evaluate(k,v);
            connectNfa(root,subFa);
        });

        return root;
    }

    /**
     * 将子nfa连接到根nfa上
     * @param root 根nfa
     * @param subFa 子nfa
     */
    private void connectNfa(FA root,FA subFa){
        root.getStartState().addEdge(null,subFa.getStartState());
    }

}
