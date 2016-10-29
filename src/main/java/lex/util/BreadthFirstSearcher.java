package lex.util;

import lex.regex.fa.State;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by sbin on 2016/10/29.
 */
public class BreadthFirstSearcher {

    /**
     *
     * @param starts 开始节点列表
     * @param next 一个function,传入当前节点,返回后继节点.因为每个节点都会被传入
     *             ,这里可以针对每个节点做额外操作,如输出之类的
     */
    public static <T> void search
            (Collection<T> starts, Function<T,Collection<T>> next){
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        queue.addAll(starts);
        while (!queue.isEmpty()){
            T current = queue.poll();
            boolean exist = visited.contains(current);
            if(!exist){
                queue.addAll(next.apply(current));
                visited.add(current);
            }
        }
    }

}
