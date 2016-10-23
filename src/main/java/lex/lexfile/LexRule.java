package lex.lexfile;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Lex定义的一条转换规则 模式(regex) : {动作}
 */
@Data
@AllArgsConstructor
public class LexRule {

    private String id;
    private String regex;

    //格式是 { some code }
    private String action;

}
