package lex.regex.dfaBuilder;

import com.google.common.collect.*;
import lex.regex.fa.FA;
import lex.regex.fa.State;
import lex.util.BreadthFirstSearcher;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sbin on 2016/10/28.
 */
public class DFABuilder {

    EpsnClosureComputer epsnClosureComputer = new EpsnClosureComputer();

    public FA convertToDFA(FA fa){
        //闭包及其对应的新state的map
        HashMap<Set<State>,State> closureMap
                = new HashMap<>();

        Set<State> startEpsnClosure
                = epsnClosureComputer.calculateEpsnClosure(fa.getStartState());

        FA dfa = new FA("dfa");
        closureMap.put(startEpsnClosure,dfa.getStartState());

        BreadthFirstSearcher.search(Arrays.asList(startEpsnClosure),
                    closure -> addEdgeAndComputeNextClosure(closure,closureMap)
                );

        return dfa;
    }

    private Collection<Set<State>> addEdgeAndComputeNextClosure
            (Set<State> closure,HashMap<Set<State>,State> closureMap){

        //当前闭包对应的state,因为能被遍历到,所以对应的state已经存在了
        State closureState = closureMap.get(closure);

        HashMultimap<Integer,State> edgeMap = computeEdgeMap(closure);
        edgeMap.asMap().forEach((edge,oneClosure)->{
            // 假如还没有包含这个闭包,就加进去.
            // 假如还没包含,说明广搜中也还没搜到,所以new一个新的state没问题
            if(!closureMap.containsKey(oneClosure)){
                closureMap.put(
                        (Set<State>)oneClosure,
                        createStateByClosure((Set<State>)oneClosure));
            }
            //将closure对应的state加一条边指向新计算出的closure对应的state
            closureState.addEdge(edge,closureMap.get(oneClosure));
        });


        return edgeMap
                .keys()
                .stream()
                .map(edgeMap::get)
                .collect(Collectors.toList());

    }

    private State createStateByClosure(Set<State> closure){
        State state = new State();

        closure.forEach(s -> {
            if (s.isAccept()){
                assert s.getPatternIds().size()==1;
                state.setAccept(s.getFirstPatternId());
            }
        });

        return state;
    }

    /**
     *
     * @param stateList 一个epsn闭包
     * @return 边和对应的epsn闭包
     */
     HashMultimap<Integer,State> computeEdgeMap(Iterable<State> stateList){
        //值是set<State> 不会重复
        HashMultimap<Integer,State> edgeMap = HashMultimap.create();
        for(State state : stateList){
            for(Integer edge : state.getNonEpsilonEdge()){
                edgeMap.putAll(edge,state.move(edge));
            }
        }

        HashMultimap<Integer,State> edgeClosureMap = HashMultimap.create();
        for(Integer edge:edgeMap.keys()){
            Set<State> epsnClosure
                    = epsnClosureComputer.calculateEpsnClosure(edgeMap.get(edge));
            edgeClosureMap.putAll(edge,epsnClosure);
        }
        return edgeClosureMap;
    }

}
