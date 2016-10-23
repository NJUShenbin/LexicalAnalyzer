package lex.lexfile;

import java.util.List;
import java.util.regex.Pattern;
import static lex.util.SimplePatternMatcher.matchFirst;

/**
 * Lex文件解析器
 */
public class LexFileParser {

    Pattern startPattern = Pattern.compile(
            "(?<=%\\{).*(?=\\}%)"
            ,Pattern.DOTALL);

    Pattern definePattern = Pattern.compile(
            "(?<=\\}%).*?(?=%%)"
            ,Pattern.DOTALL);

    Pattern rulePattern = Pattern.compile(
            "(?<=%%).*(?=%%)",
            Pattern.DOTALL);

    public LexFileInfo parse(String path){
        String fileContent = new LexFileReader().readFile(path);
        String startPart = getStartPart(fileContent);

        Definitions definitions = getDefinitions(fileContent);

        List<LexRule> rules = getRulePart(fileContent,definitions);
        String endPart = getEndPart(fileContent);

        return new LexFileInfo(startPart,endPart,rules);
    }

    private String getStartPart(String content){
        return matchFirst(content,startPattern);
    }

    private Definitions getDefinitions(String content){
        String definePart = "";
        if(getStartPart(content)!=null){
            definePart = matchFirst(content,definePattern);
        }
        else {
            int index = content.indexOf("%%");
            definePart = content.substring(0,index).trim();
        }
        return new Definitions(definePart);
    }

    private List<LexRule> getRulePart(String content, Definitions def){
        String ruleString = matchFirst(content,rulePattern);
        LexRuleParser parser = new LexRuleParser(def,ruleString);
        return parser.getRules();
    }

    private String getEndPart(String content){
        int lastIndex = content.lastIndexOf("%%");
        return content.substring(lastIndex+2).trim();
    }



}
