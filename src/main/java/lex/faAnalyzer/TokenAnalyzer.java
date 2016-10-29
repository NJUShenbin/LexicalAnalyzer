package lex.faAnalyzer;

import lex.regex.fa.FA;
import lex.regex.fa.State;

/**
 * Created by sbin on 2016/10/30.
 */
public class TokenAnalyzer {

    FA fa;
    State currentState = null;
    public TokenAnalyzer(FA fa){
        this.fa = fa;
        currentState = fa.getStartState();
    }

    String readyPatternId = null;
    String match = "";

    public MatchResult analyze(Integer c){
        if(currentState.getNonEpsilonEdge().contains(c)){
            currentState = currentState.moveToFirst(c);
            match += (char)c.intValue();
            if(currentState.isAccept()){
                readyPatternId = currentState.getFirstPatternId();
            }
            return null;
        }else {
            MatchResult result = new MatchResult(match,readyPatternId);
            readyPatternId = null;
            match = "";
            currentState = fa.getStartState();
            return result;
        }
    }

}
