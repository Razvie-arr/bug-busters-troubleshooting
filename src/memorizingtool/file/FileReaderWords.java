package memorizingtool.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderWords implements CustomFileReader<String> {

    private final ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> read(String fileName) throws IOException {
        list.clear();
        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        list.addAll(lines);
        return list;
    }

}
