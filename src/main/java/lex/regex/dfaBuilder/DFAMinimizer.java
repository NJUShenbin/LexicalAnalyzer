package lex.regex.dfaBuilder;

import com.google.common.collect.Sets;
import lex.regex.fa.FA;
import lex.regex.fa.State;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Created by sbin on 2016/10/28.
 */
public class DFAMinimizer {

    GroupDivider divider = new GroupDivider();
    MinDfaBuilder minDfaBuilder = new MinDfaBuilder();

    public FA minimizer(FA dfa){

        Set<Set<State>> groups = initDivide(dfa);

        while (true){
            Set<Set<State>> newGroups = divideGroups(groups);

            if(groups.equals(newGroups)){
                break;
            }else {
                groups = newGroups;
            }
        }

        return minDfaBuilder.build(groups,dfa.getStartState());
    }

    private Set<Set<State>> divideGroups(Set<Set<State>> groups){
        Set<Set<State>> newGroups = new HashSet<>();

        groups.forEach(group->{
            newGroups.addAll(divider.divide(group,groups));
        });
        return newGroups;
    }

    private Set<Set<State>> initDivide(FA dfa){
        Set<HashSet<State>> acceptSet = new HashSet<>();
        HashSet<State> unAcceptSet = new HashSet<>();

        dfa.forEach(s->{
            if(s.isAccept()){
                //这里要把不同结束状态分为不同组
                acceptSet.add(Sets.newHashSet(s));
            }else{
                unAcceptSet.add(s);
            }
        });

        Set<Set<State>> result = new HashSet<>(acceptSet);
        result.add(unAcceptSet);
        return result;
    }

    private class Change{
        @Setter
        @Getter
        private boolean change = true;
    }

}
