package lex.regex;

import lex.util.BracketRemover;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static lex.util.SimplePatternMatcher.matchAll;
/**
 * 消除. * | ( )之外的运算符,目前只有 [ 需要消除
 * 去除多余括号
 */
public class OperatorMinimizer {
    private String squareBracketRegex = "\\[.*?\\]";
    private Pattern squareBracketPattern = Pattern.compile(squareBracketRegex);


    public String minimize(String regex){
        List<String> squareBrackets = matchAll(regex,squareBracketPattern);

        List<String> transformed = transSquareBracket(squareBrackets);
        for (String s : transformed){
            s = s.replace("\\","\\\\");
            regex = regex.replaceFirst(squareBracketRegex,s);
        }

        return regex;
    }

    private List<String> transSquareBracket(List<String> squareBrackets){
        List<String> results = new ArrayList<>();
        squareBrackets.forEach(s->{
            //turn [abc] to abc
            s = s.substring(1,s.length()-1);
            StringBuilder builder = new StringBuilder("(");

            //turn abc to (a|b|c)
            for (int i=0;i<s.length();i++){
                char c = s.charAt(i);
                if (c=='\\'){
                    builder.append(c);
                    i++;
                    builder.append(s.charAt(i));
                    builder.append("|");
                    continue;
                }

                if (SpecialChar.isSpecial(c)){
                    builder.append("\\");
                }
                builder.append(c);
                builder.append("|");
            }
            builder.setCharAt(builder.length()-1,')');

            //去掉多余括号
//            String builderString = BracketRemover.removeBracket(builder.toString());
            String builderString = builder.toString();
            results.add(builderString);

        });

        return results;
    }
}
