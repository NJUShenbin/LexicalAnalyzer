package lex.regex.fa;

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
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        queue.add(startState);

        while (!queue.isEmpty()){
            State current = queue.poll();
            boolean notExist = visited.add(current);
            if(notExist){
                consumer.accept(current);
                queue.addAll(current.nextStates());
            }
        }
    }

    public void reset(){
        currentState = startState;
        currentPath.clear();
    }

    public void print(){
        this.forEach(state -> {
            for(State oneState : state.move(null)){
                System.out.println(state.name+" --epsn--> "+oneState.name);
            }

            for (Integer edge : state.getNonEpsilonEdge()){
                for (State oneState : state.move(edge)){
                    System.out.println(state.name+" --"+edge+"--> "+oneState.name);
                }
            }

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