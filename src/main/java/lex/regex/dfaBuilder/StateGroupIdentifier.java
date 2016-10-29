package lex.regex.dfaBuilder;

import lex.regex.fa.State;
import lombok.Getter;

import java.util.Set;
import java.util.TreeMap;

/**
 * Created by sbin on 2016/10/29.
 */
public class StateGroupIdentifier {
    @Getter
    private TreeMap<Integer,Set<State>> edgeGroupMap = new TreeMap<>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        edgeGroupMap.forEach((i,set)->{
            builder.append(i+"->Set"+set.hashCode()+";");
        });
        return builder.toString();
    }
}
