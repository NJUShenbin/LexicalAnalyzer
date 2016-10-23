package lex.lexfile;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/10/23.
 */
public class LexFileReader {

    public String readFile(String path){
        File lexFile = new File(path);
        String fileContent = "";
        try {
            List<String> lines = Files.readLines(lexFile, Charsets.UTF_8);
            lines = lines.stream()
                    .filter(s -> !s.matches("\\s*#.*"))
                    .collect(Collectors.toList());
            fileContent = Joiner.on("\n").join(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

}
