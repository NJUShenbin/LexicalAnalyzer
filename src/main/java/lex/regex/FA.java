package lex.regex;

import java.util.ArrayList;
import java.util.List;

/**
 * 有限状态机
 */
public class FA {

    protected State startState;
    protected State currentState;

    protected List<State> currentPath = new ArrayList<>();

    public boolean transform(Character c){
        return false;
    }


    public void reset(){
        currentState = startState;
        currentPath.clear();
    }

}
