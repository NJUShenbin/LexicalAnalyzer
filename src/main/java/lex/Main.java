package lex;

import lex.lexfile.LexRule;
import lex.mainFlow.Lex;
import lex.mainFlow.TargetFileName;


/**
 * 入口
 */
public class Main {

    public static void main(String[] args) {
//        String fileName = "./myLex-test.lex";
        String fileName = "./myLex.lex";
        TargetFileName.name = "./input.java";
        new Lex().generate(fileName);

    }
}
