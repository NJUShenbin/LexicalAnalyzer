package lex.regex.regexProceser;

/**
 * Created by admin on 2016/10/24.
 */
public class Operand implements Element {

    enum Special{
        dot,tab,newLine,
    }

    int value;
    Special special = null;


}
