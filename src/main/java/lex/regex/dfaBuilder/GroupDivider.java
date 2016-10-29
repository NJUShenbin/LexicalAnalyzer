package lex.regex.dfaBuilder;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lex.regex.fa.State;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sbin on 2016/10/29.
 */
public class GroupDivider {

    public Set<Set<State>> divide(Set<State> group,Set<Set<State>> groupSet){

        HashMultimap<String,State> groupMap =
                HashMultimap.create();

        group.forEach(state ->
                groupMap.put(computeIdentifier(state,groupSet).toString()
                        ,state));

        Set<Set<State>> result = new HashSet<>();
        for(String identifier:groupMap.keys()){
            result.add(groupMap.get(identifier));
        }

        return result;
    }

    private StateGroupIdentifier computeIdentifier
            (State state,Set<Set<State>> groupSet){
        assert state.move(null).isEmpty();

        StateGroupIdentifier identifier = new StateGroupIdentifier();

        for(Integer i : state.getNonEpsilonEdge()){
            assert state.move(i).size()==1;
            State next = state.move(i).iterator().next();

            for(Set<State> group:groupSet){
                if(group.contains(next)){
                    identifier.getEdgeGroupMap().put(i,group);
                    break;
                }
            }
        }

        return identifier;
    }

}
