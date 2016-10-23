package lex.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2016/10/23.
 */
public class SimplePatternMatcher {

    public static String matchFirst(String s, Pattern p){
        Matcher matcher =p.matcher(s);
        while (matcher.find()){
            return matcher.group().trim();
        }
        return null;
    }

    public static List<String> matchAll
            (String s, Pattern p){

        List<String> strings = new ArrayList<>();
        Matcher matcher =p.matcher(s);
        while (matcher.find()){
            strings.add(matcher.group());
        }

        return strings;
    }

}
