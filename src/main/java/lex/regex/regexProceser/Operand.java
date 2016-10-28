package lex.regex.regexProceser;

import lombok.Getter;

/**
 * Created by admin on 2016/10/24.
 */
public class Operand implements Element {

    private String origin = null;

    public enum Special{
        //dot的处理,假如传入 . 当做匹配任意,假如传入 \. 当做普通的 .
        dot,tab,newLine,
    }

    @Getter
    private int value;
    @Getter
    private Special special = null;

    public Operand(char c){

        origin = c+"";

        if(c!='.'){
            value = c;
        }else{
            special = Special.dot;
        }

    }

    public Operand(String specialOp){
        origin = specialOp;

        switch (specialOp){
            case "\\t" :
                special = Special.tab;
                break;
            case "\\n":
                special = Special.newLine;
                break;
            case "\\.":
                value = '.';
                break;
        }
    }

    public boolean isSpecial(){
        return special!=null;
    }

    @Override
    public String toString() {
        return origin;
    }
}
