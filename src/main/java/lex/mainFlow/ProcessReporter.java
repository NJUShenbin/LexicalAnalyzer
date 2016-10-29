package lex.mainFlow;

import lex.regex.fa.FA;
import lombok.Getter;

/**
 * Created by sbin on 2016/10/30.
 */
public class ProcessReporter {

    public static void analyzeFile(){
        System.out.println("正在分析lex文件");
    }


    public static void constructNFA(FA fa){
        Counter counter = new Counter();
        fa.forEach(s->counter.count());
        System.out.println("构造NFA,状态数为"+counter.getCount());
    }


    public static void constructDFA(FA fa){
        Counter counter = new Counter();
        fa.forEach(s->counter.count());
        System.out.println("构造DFA,状态数为"+counter.getCount());
    }

    public static void minimizeDFA(FA fa){
        Counter counter = new Counter();
        fa.forEach(s->counter.count());
        System.out.println("最小化DFA,状态数为"+counter.getCount());
    }

    private static class Counter{
        @Getter
        int count = 0;

        public void count(){
            count++;
        }

    }

}
