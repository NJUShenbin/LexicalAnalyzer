package lex;

import lex.lexfile.LexRule;
import lex.mainFlow.Lex;


/**
 * 入口
 */
public class Main {

    public static void main(String[] args) {
        String fileName = "./myLex-test.lex";
//        String fileName = "./myLex.lex";
        new Lex().generate(fileName);

    }
}
