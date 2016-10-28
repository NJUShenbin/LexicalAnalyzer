package lex.regex.regexProceser;


import lombok.Getter;

public class Operator implements Comparable<Operator>,Element{

    private char operator;
    private int priority;

    //用来充当连接运算符
    @Getter
    private boolean connection = false;
    private boolean single = false;

    private String origin = null;

    //是否是连接运算符
    public Operator(){
        // 随便找个符号表示连接,用于输出测试,和实际逻辑没关系.
        origin = "●";
        connection = true;
        priority = 2;
    }


    //最高是   ( )
    //其次     * +
    //其次     连接
    //其次     |

    public Operator(char operator){
        origin = ""+operator;
        this.operator = operator;

        switch(operator){
            case '(':
                priority = 0;
                break;
            case '|':
                priority = 1;
                break;

            case '+':
                priority = 4;
                single = true;
                break;
            case '*':
                priority = 4;
                single = true;
                break;

        }


    }

    public Operand excute(Operand a,Operand b){
        return null;
    }

    public boolean isSingle() {
        return single;
    }

    public char getOperator() {
        return operator;
    }

    public String toString(){
        return origin+"";
    }

    @Override
    public int compareTo(Operator o) {
        if(operator=='('){
            return 1;
        }

        if(o.operator=='('){
            return 1;
        }

        else{
            Integer a = new Integer(priority);
            Integer b = new Integer(o.priority);
            return a.compareTo(b);
        }

    }



}