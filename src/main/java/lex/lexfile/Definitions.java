package lex.lexfile;


import com.google.common.base.Strings;
import lex.util.BracketRemover;

import java.util.*;
import java.util.regex.Pattern;

import static lex.util.SimplePatternMatcher.matchAll;

/**
 * Created by admin on 2016/10/23.
 */
public class Definitions {

    Map<String,String> definitionMap = new LinkedHashMap<>();

    //如 {letter}({letter}|{digit})* , 则匹配到 {letter},{letter},{digit}
    Pattern varPattern = Pattern.compile("(?<!\\\\)\\{.*?\\}");

    public Definitions(String definePart){
        if(Strings.isNullOrEmpty(definePart)){
            return;
        }
        Arrays.stream(definePart.split("\n")).forEach(s -> {
            if(Strings.isNullOrEmpty(s)){
                return;
            }
            String[] pair = s.split("\\s+",2);
            definitionMap.put(pair[0],pair[1]);
        });

        definitionMap.forEach( (k,v) ->{
            definitionMap.put(k,replaceDefinitions(v));
        });

    }

    public String replaceDefinitions(String regex){

        for (String name : matchAll(regex,varPattern)){
            //if name is {a},name in map is a
            String nameInMap = name.substring(1,name.length()-1);
            String value = definitionMap.get(nameInMap);
            regex = regex.replace(name,"("+value+")");
        }

        regex = BracketRemover.removeBracket(regex);

        return regex;
    }

}
