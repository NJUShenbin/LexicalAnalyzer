package lex.regex;

import java.util.List;
import java.util.Map;

/**
 * 有限状态机中的状态
 */
public abstract class State {

    boolean accept;
    String patternId;

    public boolean isAccept(){
        return accept;
    }

    public String getPatternId(){
        if (isAccept()){
            return patternId;
        }else{
            throw new RuntimeException("this state is not accepted state");
        }
    }

}
