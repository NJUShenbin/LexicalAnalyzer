package lex.lexfile;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import static lex.util.SimplePatternMatcher.matchAll;

/**
 * Created by admin on 2016/10/23.
 */
public class LexRuleParser {

    private Definitions definitions;

    @Getter
    private List<LexRule> rules = new ArrayList<>();

    private Pattern rulePattern = Pattern.compile(".*?:\\s*\\{.*?\\}",Pattern.DOTALL);

    public LexRuleParser(Definitions def,String ruleString){
        definitions = def;
        List<String> ruleStrings = matchAll(ruleString,rulePattern);
        Map<String,String> ruleMap = new LinkedHashMap<>();

        ruleStrings.forEach(s->{
            String[] splits = s.split(":",2);
            String regex = splits[0].trim();
            String action = splits[1].trim();
            action = action.substring(1,action.length()-1).trim();
            regex = definitions.replaceDefinitions(regex);

            ruleMap.put(regex,action);
        });


        IdGenerator generator = new IdGenerator();
        ruleMap.forEach((k,v)->{
            //此时{和}已经替换完毕,可以把转义符去掉
            k = k.replaceAll("\\\\\\{","{");
            k = k.replaceAll("\\\\\\}","}");
            rules.add(new LexRule(generator.generate(),k,v));
        });

    }

    private class IdGenerator{
        private int id = -1;
        public String generate(){
            id++;
            return id+"";
        }
    }
}
