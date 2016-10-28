package lex.regex;

import com.google.common.base.Joiner;
import lex.regex.regexProceser.Element;
import lex.regex.regexProceser.RegexProcesser;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 将 pattern-regexString map转化为 pattern和对应的regex对象序列map
 */
public class PatternRegexElementMap {

    private RegexProcesser regexProcesser = new RegexProcesser();

    @Getter
    @Setter
    private Map<String,String> patternRegexMap;

    @Getter
    @Setter
    private Map<String,List<Element>> regexElementMap = new LinkedHashMap<>();


    public PatternRegexElementMap(Map<String,String> patternRegexMap){
        this.patternRegexMap = patternRegexMap;

        patternRegexMap.forEach((k,v)->{
            List<Element> elementList = regexProcesser.process(v);
            regexElementMap.put(k,elementList);
        });

//        regexElementMap.forEach((k,v)->{
//            System.out.printf(k+":");
//            System.out.println(Joiner.on("").join(v));
//            System.out.println("---");
//        });

    }


}
