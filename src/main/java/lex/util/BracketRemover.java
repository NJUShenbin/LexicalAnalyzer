package lex.util;

/**
 * 去除多余括号.
 */
public class BracketRemover {

    public static String removeBracket(String s){

        if(s.charAt(0)!='('){
            return s;
        }

        int count = 0;

        for(int i=0; i<s.length() ; i++){
            char c = s.charAt(i);

            if(c=='('){
                count++;
            }else if (c==')'){
                count--;
            }
            //最后一个括号使count变成0
            if (count==0 && i==s.length()-1){
                return s.substring(1,s.length()-1);
            }else if(count == 0 && i!=s.length()-1){
                return s;
            }
        }

        return s;

    }

}
