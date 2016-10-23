package lex.regex;

/**
 * Created by admin on 2016/10/23.
 */
public class SpecialChar {
    private static String specialChars = ".*|()";
    public static boolean isSpecial(char c){
        return (specialChars.indexOf(c)!=-1);
    }
}
