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
    TreeSet<Integer> patternIds = new TreeSet<>();

    Multimap<Integer,State> multimap = LinkedHashMultimap.create();
    Set<State> epsilonStates = new LinkedHashSet<>();


    String name = null;

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

    public void setAccept(boolean accept) {
        this.accept = accept;
        if(!accept){
            patternIds.clear();
        }
    }

    public void setAccept(String patternId){
        accept = true;
        patternIds.add(Integer.parseInt(patternId));
    }

    public void setAccept(Collection<Integer> patternIds){
        accept = true;
        this.patternIds.addAll(patternIds);
    }

    public Collection<State> move(Integer edge){
        if(edge==null){
            return epsilonStates;
        }else {
            return multimap.get(edge);
        }
    }

    public State moveToFirst(Integer edge){
        if(edge==null){
            return epsilonStates.iterator().next();
        }else {
            return multimap.get(edge).iterator().next();
        }
    }

    public void addEdge(Integer edge,State next){
        if(edge==null){
            epsilonStates.add(next);
        }else{
            multimap.put(edge,next);
        }
    }

    public void addEdge(Integer edge,Collection<State> nextStates){
        if(edge==null){
            epsilonStates.addAll(nextStates);
        }else{
            multimap.putAll(edge,nextStates);
        }
    }


    public TreeSet<Integer> getPatternIds(){
        if (isAccept()){
            return patternIds;
        }else{
            throw new RuntimeException("this state is not accepted state");
        }
    }

    public String getFirstPatternId(){
        return patternIds.first().toString();
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
