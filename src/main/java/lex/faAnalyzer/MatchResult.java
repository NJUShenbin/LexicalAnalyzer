package lex.faAnalyzer;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sbin on 2016/10/30.
 */
@Data
@AllArgsConstructor
public class MatchResult {

    private String value = "";
    private String patternId = null;

}
