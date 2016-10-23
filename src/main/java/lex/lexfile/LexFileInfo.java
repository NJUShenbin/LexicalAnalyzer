package lex.lexfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Lex文件解析结果
 */
@Data
public class LexFileInfo {

    private String startPart;
    private String endPart;
    private List<LexRule> rules;

    public LexFileInfo(String startPart,String endPart,List<LexRule> rules){
        if (startPart==null){
            startPart = "";
        }

        if (endPart==null){
            endPart = "";
        }
        this.startPart = startPart;
        this.endPart = endPart;
        this.rules = rules;
    }

}
