package lex.regex.dfaBuilder;

import lex.regex.fa.FA;
import lex.regex.fa.State;
import lex.regex.fa.StateMerger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by sbin on 2016/10/29.
 */
public class MinDfaBuilder {

    public FA build(Set<Set<State>> groups,State startState){
        FA minDfa = new FA();
        HashMap<Set<State>,State> groupStateMap = new HashMap<>();
        groups.forEach(group->{
            if(group.contains(startState)){
                groupStateMap.put(group,minDfa.getStartState());
            }else{
                groupStateMap.put(group, StateMerger.merge(group));
            }
        });

        groups.forEach(group->{
            State oneFormerState = getFirst(group);
            State newState = groupStateMap.get(group);
            Map<Integer,Set<State>> edgeGroupMap =
                    getGroupByEdge(groups,oneFormerState);

            edgeGroupMap.forEach((edge,mappedGroup)->{
                newState.addEdge(edge,groupStateMap.get(mappedGroup));
            });

        });
        return minDfa;
    }

    private <T> T getFirst(Collection<T> states){
        return states.iterator().next();
    }

    private Map<Integer,Set<State>> getGroupByEdge
            (Set<Set<State>> groups,State formerState){

        Map<Integer,Set<State>> edgeGroupMap = new HashMap<>();

        assert formerState.move(null).isEmpty();

        for(Integer edge : formerState.getNonEpsilonEdge()){
            assert formerState.move(edge).size()==1;

            State nextState = getFirst(formerState.move(edge));
            Set<State> containerGroup = getContainerGroup(groups,nextState);
            edgeGroupMap.put(edge,containerGroup);
        }

        return edgeGroupMap;
    }

    private Set<State> getContainerGroup(Set<Set<State>> groups,State state){
        for(Set<State> group:groups){
            if(group.contains(state)){
                return group;
            }
        }
        throw new RuntimeException("no such state");
    }

}
