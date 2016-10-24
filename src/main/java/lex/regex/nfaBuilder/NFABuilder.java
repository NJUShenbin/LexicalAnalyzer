package lex.regex.nfaBuilder;

import lex.regex.regexProceser.Element;

import java.util.List;
import java.util.Map;

/**
 * 根据 pattern-regex构造NFA
 */
public class NFABuilder {

    Map<String,List<Element>> regexElementMap;

    public NFABuilder(Map<String,List<Element>> regexElementMap){
        this.regexElementMap = regexElementMap;
    }

}
