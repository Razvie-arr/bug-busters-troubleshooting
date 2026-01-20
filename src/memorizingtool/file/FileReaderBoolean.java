package memorizingtool.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//approached the bushes and discovered a beautifully crafted silver key. The key...
public class FileReaderBoolean implements CustomFileReader<Boolean> {

    private final ArrayList<Boolean> list = new ArrayList<>();

    public ArrayList<Boolean> read(String fileName) throws IOException {
        list.clear();
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        for (String s : lines) {
            list.add(Boolean.parseBoolean(s));
        }
        return list;
    }

}