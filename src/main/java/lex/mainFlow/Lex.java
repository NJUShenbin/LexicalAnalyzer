package lex.mainFlow;

import lex.generator.LexGenerator;
import lex.lexfile.LexFileInfo;
import lex.lexfile.LexFileParser;
import lex.regex.FA;
import lex.regex.RegexCompiler;

/**
 * Created by admin on 2016/10/23.
 */
public class Lex {

    private LexFileParser lexFileParser = new LexFileParser();
    private RegexCompiler regexCompiler = new RegexCompiler();
    private LexGenerator lexGenerator = new LexGenerator();


    public void generate(String filePath){

        LexFileInfo info = lexFileParser.parse(filePath);
        PatternMap patternMap = new PatternMap(info.getRules());

        FA fa = regexCompiler.compile(patternMap.getPatternRegexMap());

        lexGenerator.generate(fa,patternMap.getPatternActionMap(),
            info.getStartPart(),info.getEndPart());

    }

}
