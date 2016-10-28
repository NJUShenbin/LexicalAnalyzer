package lex.regex.nfaBuilder;

import lex.regex.fa.FA;
import lex.regex.fa.State;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sbin on 2016/10/28.
 */
@Data
public class NFAWrapper {
    private State start;
    private State end;
    private FA fa;

    public NFAWrapper(FA fa,State end){
        this.fa = fa;
        this.start = fa.getStartState();
        this.end = end;
    }

}
