package lex.regex;

import lex.regex.dfaBuilder.DFABuilder;
import lex.regex.dfaBuilder.DFAMinimizer;
import lex.regex.fa.FA;
import lex.regex.nfaBuilder.NFABuilder;

import java.util.Map;

/**
 * 正则表达式编译器,将正则表达式编译为DFA,DFA的接受状态有对应模式的id
 */
public class RegexCompiler {

    OperatorMinimizer minimizer = new OperatorMinimizer();
    NFABuilder nfaBuilder = new NFABuilder();
    DFABuilder dfaBuilder = new DFABuilder();
    DFAMinimizer dfaMinimizer = new DFAMinimizer();
    /**
     * 编译表达式
     * @param patternRegexMap 模式id-表达式 表
     */
    public FA compile(Map<String,String> patternRegexMap){
        //转化regex中的特殊运算符
        minimizeEachRegex(patternRegexMap);
//        System.out.println("-----");
//        System.out.println(patternRegexMap);
        //将regex从字符序列变为对象序列,方便处理
        PatternRegexElementMap elementMap =
                new PatternRegexElementMap(patternRegexMap);

        //正则表达式->NFA
        FA nfa = nfaBuilder.build(elementMap.getRegexElementMap());
        //NFA->DFA
        FA dfa = dfaBuilder.convertToDFA(nfa);
        //DFA->DFA°
        dfa = dfaMinimizer.minimizer(dfa);

        dfa.resetIncreasedId();
        dfa.print();

        return null;
    }

    private void minimizeEachRegex(Map<String,String> patternRegexMap){
        patternRegexMap.forEach((k,v)->{
            v = minimizer.minimize(v);
            patternRegexMap.put(k,v);
        });
    }

}
