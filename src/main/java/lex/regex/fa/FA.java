package lex.regex.fa;

import lex.util.BreadthFirstSearcher;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;

/**
 * 有限状态机
 */
public class FA {

    protected State startState;
    protected State currentState;
    protected List<State> currentPath = new ArrayList<>();

    StateIdGenerator generator = new StateIdGenerator();

    @Getter
    @Setter
    String name;

    public FA(){
        this("FA");
    }

    public FA(String name){
        this.name = name;
        startState = createState();
        currentState = startState;
    }

    public boolean transform(String s){
        return false;
    }

    public State getStartState() {
        return startState;
    }

    public State createState(){
        return new State(generator.generate());
    }

    public void forEach(Consumer<State> consumer){
        //广度优先搜索
        BreadthFirstSearcher.search(
                Arrays.asList(startState),
                state ->{
                    consumer.accept(state);
                    return state.nextStates();
                });
    }

    public void reset(){
        currentState = startState;
        currentPath.clear();
    }

    public void print(){
        this.forEach(state -> {
            for(State oneState : state.move(null)){
                printOneEdge(state,oneState,null);
            }

            for (Integer edge : state.getNonEpsilonEdge()){
                for (State oneState : state.move(edge)){
                    printOneEdge(state,oneState,edge);
                }
            }

        });
    }

    private void printOneEdge(State start,State end,Integer edge){
        String stateName = start.name;
        String oneStateName = end.name;

        if(start.isAccept()){
            stateName+="(a)";
        }
        if(end.isAccept()){
            oneStateName+="(a)";
        }

        if(edge!=null){
            System.out.println(stateName+" --"
                    +(char)edge.intValue()
                    +"--> "+oneStateName);
        }else {
            System.out.println(stateName+" --epsn--> "+oneStateName);
        }

    }

    /**
     * 将所有state id重置为递增的,方便debug
     */
    public void resetIncreasedId(){
        StateIdGenerator generator = new StateIdGenerator();
        this.forEach(s->{
            s.setName(generator.generate());
        });
    }

    class StateIdGenerator{
        int id=-1;
        public String generate(){
            id++;
            return name+"-s"+id;
        }
    }

}
