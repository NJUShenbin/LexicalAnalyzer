package lex.regex.regexProceser;


public class Operator implements Comparable<Operator>,Element{

    char operator;
    int priority;
    boolean isConnection = false;
    boolean single = false;

    //是否是连接运算符
    public Operator(){
        isConnection = true;
        priority = 2;
    }


    //最高是   ( )
    //其次     * +
    //其次     连接
    //其次     |
    public Operator(char operator){
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
                break;
            case '*':
                priority = 4;
                break;

        }


    }

    public Operand excute(Operand a,Operand b){
        return null;
    }

    public boolean isSingle() {
        return single;
    }


    public String toString(){
        return operator+"";
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