package lex.regex.regexProceser;

import lex.regex.SpecialChar;

/**
 * 判断一个字符是否为运算符
 */
public class OperatorJudger {
    private static String operatorChars = "*|()+";

    public static boolean isOperator(char c){
        return (operatorChars.indexOf(c) != -1);
    }
}
