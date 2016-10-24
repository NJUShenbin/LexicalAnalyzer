package lex.regex.fa;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * 有限状态机中的状态
 *
 * inreger作为edge,若为null,视为epsilon边
 */
public class State {

    boolean accept;
    String patternId;

    Multimap<Integer,State> multimap = LinkedHashMultimap.create();
    Set<State> epsilonStates = new LinkedHashSet<>();


    String name = "default";

    public State(){}

    public State(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccept(){
        return accept;
    }

    public Collection<State> move(Integer edge){
        if(edge==null){
            return epsilonStates;
        }else {
            return multimap.get(edge);
        }
    }

    public void addEdge(Integer edge,State next){
        if(edge==null){
            epsilonStates.add(next);
        }else{
            multimap.put(edge,next);
        }
    }

    public String getPatternId(){
        if (isAccept()){
            return patternId;
        }else{
            throw new RuntimeException("this state is not accepted state");
        }
    }

    public Set<State> nextStates(){
        Set<State> nextStates = new HashSet<>(epsilonStates);
        nextStates.addAll(multimap.values());

        return nextStates;
    }

    public Set<Integer> getNonEpsilonEdge(){
        return multimap.keySet();
    }

    public boolean hasEpsilon(){
        return !epsilonStates.isEmpty();
    }

}
