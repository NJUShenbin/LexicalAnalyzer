package lex.mainFlow;

import lex.faAnalyzer.Analyzer;
import lex.generator.LexGenerator;
import lex.lexfile.LexFileInfo;
import lex.lexfile.LexFileParser;
import lex.regex.fa.FA;
import lex.regex.RegexCompiler;

/**
 * Created by admin on 2016/10/23.
 */
public class Lex {

    private LexFileParser lexFileParser = new LexFileParser();
    private RegexCompiler regexCompiler = new RegexCompiler();
    private LexGenerator lexGenerator = new LexGenerator();
    private Analyzer analyzer = new Analyzer();

    public void generate(String filePath){

        LexFileInfo info = lexFileParser.parse(filePath);
        ProcessReporter.analyzeFile();
        PatternMap patternMap = new PatternMap(info.getRules());

        FA fa = regexCompiler.compile(patternMap.getPatternRegexMap());
        analyzer.analyze(fa,patternMap.getPatternActionMap());


//        lexGenerator.generate(fa,patternMap.getPatternActionMap(),
//            info.getStartPart(),info.getEndPart());

    }

}
