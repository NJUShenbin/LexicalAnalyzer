package lex.mainFlow;

import lex.lexfile.LexRule;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存 pattern-regex表和pattern-action表的类
 */
public class PatternMap {
    @Getter
    private Map<String,String> patternRegexMap;
    @Getter
    private Map<String,String> patternActionMap;

    public PatternMap(List<LexRule> rules){


        patternRegexMap = new LinkedHashMap<>();
        patternActionMap = new LinkedHashMap<>();

        if (rules!=null){
            for (LexRule rule : rules){
                patternRegexMap.put(rule.getId(),rule.getRegex());
                patternActionMap.put(rule.getId(),rule.getAction());
            }
        }else{
            System.out.println("rules is null");
        }


    }

}
