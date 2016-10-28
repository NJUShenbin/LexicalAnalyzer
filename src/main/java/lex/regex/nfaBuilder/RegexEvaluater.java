package lex.regex.nfaBuilder;

import lex.regex.fa.FA;
import lex.regex.fa.State;
import lex.regex.regexProceser.Element;
import lex.regex.regexProceser.Operand;
import lex.regex.regexProceser.Operator;

import java.util.List;
import java.util.Stack;

/**
 * 将一个正规表达式变为NFA
 */
public class RegexEvaluater {

    Stack<NFAWrapper> wrapperStack = new Stack<>();
    Stack<Operator> operatorStack = new Stack<>();
    RegexOperatorExecutor executor = new RegexOperatorExecutor();

    public FA evaluate(String patternId, List<Element> regex){

        for(Element element : regex){
            if(element instanceof Operand){
                wrapperStack.add(wrapOperand( (Operand)element ));
            }else if(element instanceof Operator){
                handleOperator( (Operator)element );
            }
        }

        while(!operatorStack.empty()){
            executor.popAndExecute(operatorStack.pop(),wrapperStack);
        }

        return wrapperStack.pop().getFa();
    }

    private void handleOperator(Operator op){
        if(op.getOperator()==')'){
            while(operatorStack.peek().getOperator()!='('){
                executor.popAndExecute(operatorStack.pop(),wrapperStack);
            }
            //弹出左括号
            operatorStack.pop();
        }else{
            while((!operatorStack.isEmpty())
                    &&(op.compareTo(operatorStack.peek())<=
                    0)){
                executor.popAndExecute(operatorStack.pop(),wrapperStack);
            }
            operatorStack.push(op);
        }
    }

    /**
     * 基本规则,根据单个字符获得状态机
     * @param operand
     * @return
     */
    private NFAWrapper wrapOperand(Operand operand){
        FA fa = new FA();
        State start = fa.getStartState();
        State end = fa.createState();
        end.setAccept(true);

        if (operand.isSpecial()){
            switch (operand.getSpecial()){
                case dot:
                    handleDot(start,end);
                    break;
                case tab:
                    start.addEdge((int)'\t',end);
                    break;
                case newLine:
                    start.addEdge((int)'\n',end);
                    break;
                default:
                    throw new RuntimeException("unknown special char");
            }
        }else {
            start.addEdge(operand.getValue(),end);
        }

        return new NFAWrapper(fa,end);
    }

    private void handleDot(State start,State end){
        String every = "abcdefghijklmnopqrstuvwxyz1234567890";
        every += every.toUpperCase();
//        every +="`~!@#$%^&*()_+1234567890-={}[]'\";<>,./?\\|";

        for(char c:every.toCharArray()){
            start.addEdge((int)c,end);
        }

    }

}
