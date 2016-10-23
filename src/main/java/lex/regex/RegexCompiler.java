package lex.regex;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 正则表达式编译器,将正则表达式编译为DFA,DFA的接受状态有对应模式的id
 */
public class RegexCompiler {

    OperatorMinimizer minimizer = new OperatorMinimizer();

    /**
     * 编译表达式
     * @param patternRegexMap 模式id-表达式 表
     */
    public FA compile(Map<String,String> patternRegexMap){
        patternRegexMap.forEach((k,v)->{
            v = minimizer.minimize(v);
            patternRegexMap.put(k,v);
        });

        System.out.println("-----");
        System.out.println(patternRegexMap);

        return null;
    }

}
