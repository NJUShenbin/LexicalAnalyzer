package lex.regex.regexProceser;

import java.util.ArrayList;
import java.util.List;

import static lex.regex.regexProceser.OperatorJudger.isOperator;

/**
 * 将正则表达式变成对象序列,并且添加连接运算符
 * <br>
 * 加连接运算符的情况:
 * 1. 连续两个字符间
 * 2. 连续) 和 (间
 * 3. 单目运算符后边的 (
 * 3. 单目运算符之后的字符
 * 4. ) 后边是字符
 * 5. 字符后边是 (
 *
 */
public class RegexProcesser {

    public List<Element> process(String regex){
        List<Element> elementList = new ArrayList<>();

        //将正则表达式变成对象,并且添加连接运算符
        for(int i=0;i<regex.length();i++){
            char c = regex.charAt(i);

            //如果是 \ 那么后边当做字符处理
            if(c=='\\'){
                i++;
                c = regex.charAt(i);
                elementList.add(new Operand("\\"+c));
                addCatOperator(elementList);
                continue;
            }

            if(isOperator(c)){
                elementList.add(new Operator(c));
            }else{
                elementList.add(new Operand(c));
            }
            addCatOperator(elementList);
        }

        return elementList;
    }


    //为刚添加元素的列表补上连接运算符.
    private void addCatOperator(List<Element> elementList){
        int size = elementList.size();

        if(size<=1){
            return;
        }

        Element lastButOne = elementList.get(size-2);
        Element last = elementList.get(size-1);

        // 对应5种情况补充连接运算符
        // 连续字符间
        if(lastButOne instanceof Operand && last instanceof Operand){
            insertCat(elementList);
            return;
        }

        else if(lastButOne instanceof Operator && last instanceof Operator){
            // ) 和 ( 间
            if( ((Operator) lastButOne).getOperator()==')' && ((Operator) last).getOperator()=='('){
                insertCat(elementList);
            }
            //  单目运算符 和 ( 间
            else if(((Operator) lastButOne).isSingle() && ((Operator) last).getOperator()=='('){
                insertCat(elementList);
            }
            return;
        }

        else if(lastButOne instanceof Operator && last instanceof Operand){
            //  单目运算符 和 字符 之间
            if( ((Operator) lastButOne).isSingle() ){
                insertCat(elementList);
            }

            // )和字符之间
            if( ((Operator) lastButOne).getOperator()==')' ){
                insertCat(elementList);
            }
            return;
        }
        //字符后边是 (
        else if(lastButOne instanceof Operand && last instanceof Operator){
            if( ((Operator) last).getOperator()=='(' ){
                insertCat(elementList);
            }
            return;
        }
    }

    private void insertCat(List<Element> elementList){
        elementList.add(elementList.size()-1,new Operator());
    }

}
