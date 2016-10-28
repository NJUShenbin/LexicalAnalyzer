package lex.regex.dfaBuilder;

import com.google.common.collect.ImmutableList;
import lex.regex.fa.FA;
import lex.regex.fa.State;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sbin on 2016/10/28.
 */
public class DFABuilder {

    public FA convertToDFA(FA fa){
        HashMap<ImmutableList<State>,State> uncountedClosures
                = new HashMap<>();
        //前边是旧的状态组成的集合,作为key.new一个新状态作为值
        HashMap<ImmutableList<State>,State> countedClosures
                = new HashMap<>();

        // TODO 计算start state的闭包,加进未计算
        // TODO while(){  }


        return fa;
    }

    private ImmutableList<State> calculateEpsnClosure(List<State> states){
        //todo 这里用广搜
        //todo 用一个set和一个queue
        return null;
    }

}
