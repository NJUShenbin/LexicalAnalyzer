package lex.regex.fa;

import java.util.Set;

/**
 * Created by sbin on 2016/10/29.
 */
public class StateMerger {

    public static State merge(Set<State> stateSet){
        State state = new State();

        stateSet.forEach(s -> {
            if (s.isAccept()){
                assert s.getPatternIds().size()>=1;
                state.setAccept(s.getPatternIds());
            }
        });

        return state;
    }

}
