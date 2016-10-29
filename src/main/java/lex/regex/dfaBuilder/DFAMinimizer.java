package lex.regex.dfaBuilder;

import com.google.common.collect.Sets;
import lex.regex.fa.FA;
import lex.regex.fa.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by sbin on 2016/10/28.
 */
public class DFAMinimizer {

    GroupDivider divider = new GroupDivider();

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

        return dfa;
    }

    private Set<Set<State>> divideGroups(Set<Set<State>> groups){
        Set<Set<State>> newGroups = new HashSet<>();

        groups.forEach(group->{
            newGroups.addAll(divider.divide(group,groups));
        });
        return newGroups;
    }

    private Set<Set<State>> initDivide(FA dfa){
        HashSet<State> acceptSet = new HashSet<>();
        HashSet<State> unAcceptSet = new HashSet<>();

        dfa.forEach(s->{
            if(s.isAccept()){
                acceptSet.add(s);
            }else{
                unAcceptSet.add(s);
            }
        });

        return Sets.newHashSet(acceptSet,unAcceptSet);

    }

    private class Change{
        @Setter
        @Getter
        private boolean change = true;
    }

}
