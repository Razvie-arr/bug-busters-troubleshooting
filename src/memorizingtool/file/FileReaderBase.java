package memorizingtool.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class FileReaderBase<T> {

    public List<T> read(String fileName) throws IOException {
        List<T> list = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            lines.map(this::parse).forEach(list::add);
        }
        return list;
    }

    protected abstract T parse(String line);

}
