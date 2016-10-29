package lex.regex.dfaBuilder;

import com.google.common.collect.ImmutableList;
import lex.regex.fa.State;
import lex.util.BreadthFirstSearcher;

import java.util.*;

/**
 * Created by sbin on 2016/10/29.
 */
public class EpsnClosureComputer {

    public Set<State> calculateEpsnClosure(Collection<State> states){

        //广优搜索,最后epsnStates存的是epsn闭包
        Set<State> epsnStates = new HashSet<>();
        BreadthFirstSearcher.search(states,
                state -> {
                    epsnStates.add(state);
                    return state.move(null);
                }
        );
        return epsnStates;
    }

    public Set<State> calculateEpsnClosure(State state){
        return calculateEpsnClosure(Arrays.asList(state));
    }

}
