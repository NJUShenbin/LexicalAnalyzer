package lex.faAnalyzer;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import lex.mainFlow.TargetFileName;
import lex.regex.fa.FA;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sbin on 2016/10/30.
 */
public class Analyzer {

    public void analyze(FA fa,Map<String,String> patternActionMap){
        String filename = TargetFileName.name;
        File lexFile = new File(filename);
        String fileContent = "";
        File resultFile = new File("./analyzeResult");
        BufferedWriter writer = null;
        try {
            resultFile.createNewFile();
            writer = Files.newWriter(resultFile, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> lines = Files.readLines(lexFile, Charsets.UTF_8);
            fileContent = Joiner.on("\n").join(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TokenAnalyzer tokenAnalyzer = new TokenAnalyzer(fa);
        for(int i=0;i<fileContent.length();i++){
            MatchResult result = tokenAnalyzer.analyze((int)fileContent.charAt(i));
            if(result!=null){
                i--;
                String value = result.getValue();
                String action = patternActionMap.get(result.getPatternId());

                if(action.equals("nothing")){
                    continue;
                }
                String line = "<"
                        +action
                        +","
                        +value
                        +">";

                try {
                    writer.write(line+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(line);
            }
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
