package lex;

import lex.lexfile.LexRule;
import lex.mainFlow.Lex;


/**
 * 入口
 */
public class Main {

    public static void main(String[] args) {
        new Lex().generate("./myLex-test.lex");
    }
}
