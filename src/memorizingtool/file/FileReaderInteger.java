package memorizingtool.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderInteger implements CustomFileReader<Integer> {

    static ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> read(String fileName) throws IOException {
        list.clear();

        List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        for (String s : lines) {
            list.add(Integer.parseInt(s));

        }

        return list;
    }

}