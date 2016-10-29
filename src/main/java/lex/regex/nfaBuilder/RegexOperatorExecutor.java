package lex.regex.nfaBuilder;

import lex.regex.fa.FA;
import lex.regex.fa.State;
import lex.regex.regexProceser.Operator;

import java.util.Stack;

/**
 * Created by sbin on 2016/10/28.
 */
public class RegexOperatorExecutor {
    /**
     * 对NFA进行拼接运算
     * @param op
     * @param wrapperStack
     * @return
     */
    public void popAndExecute
            (Operator op, Stack<NFAWrapper> wrapperStack){

        NFAWrapper wrapper;

        if(op.isSingle()){
            wrapper = handleSingle(op,wrapperStack.pop());
        }else {
            wrapper = handleBinary(op,wrapperStack.pop(),wrapperStack.pop());
        }

        wrapperStack.push(wrapper);
    }

    /**
     * 处理单目运算符
     * @param op
     * @param wrapper
     * @return
     */
    private NFAWrapper handleSingle
            (Operator op, NFAWrapper wrapper){

        switch (op.getOperator()){
            case '+':
                return plusOperator(wrapper);
            case '*':
                return asteriskOperator(wrapper);
        }
        throw new RuntimeException("unknown operator");
    }

    private NFAWrapper plusOperator(NFAWrapper wrapper){
        State formerStart = wrapper.getStart();
        State formerEnd = wrapper.getEnd();

        //取消原来的接受状态
        formerEnd.setAccept(false);
        formerEnd.addEdge(null,formerStart);

        FA newFa = new FA();
        newFa.getStartState().addEdge(null,formerStart);
        State newEndState = newFa.createState();
        newEndState.setAccept(true);
        formerEnd.addEdge(null,newEndState);

        return new NFAWrapper(newFa,newEndState);
    }

    private NFAWrapper asteriskOperator(NFAWrapper wrapper){
        //*的状态机和+的状态机只差一条epsn边
        NFAWrapper plusWrapper = plusOperator(wrapper);
        plusWrapper.getStart().addEdge(null,plusWrapper.getEnd());
        return plusWrapper;
    }

    /**
     * 处理双目
     * @param op
     * @param after
     * @param before
     * @return
     */
    private NFAWrapper handleBinary
            (Operator op, NFAWrapper after , NFAWrapper before){

        if (op.isConnection()){
            return handleConnection(before,after);
        }else if(op.getOperator()=='|'){
            return handleOr(before,after);
        }

        throw new RuntimeException("unknown operator");
    }

    private NFAWrapper handleConnection(NFAWrapper before , NFAWrapper after){
        //将连接的前边去除接受状态
        before.getEnd().setAccept(false);
        State afterStart = after.getStart();

        //合并after的开始节点的所有边

        //合并非epsn边
        afterStart.getNonEpsilonEdge().forEach(edge -> {
            before.getEnd().addEdge(edge,afterStart.move(edge));
        });
        //合并epsn边
        before.getEnd().addEdge(null,afterStart.move(null));

        return new NFAWrapper(before.getFa(),after.getEnd());
    }

    private NFAWrapper handleOr(NFAWrapper before , NFAWrapper after){
        before.getEnd().setAccept(false);
        after.getEnd().setAccept(false);

        FA fa = new FA();
        fa.getStartState().addEdge(null,before.getStart());
        fa.getStartState().addEdge(null,after.getStart());

        State newEnd = fa.createState();
        newEnd.setAccept(true);
        before.getEnd().addEdge(null,newEnd);
        after.getEnd().addEdge(null,newEnd);
        return new NFAWrapper(fa,newEnd);
    }


}
