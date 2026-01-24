package memorizingtool.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public abstract class FileReaderBase<T> {

    public int read(String fileName, List<T> list) throws IOException {
        int readCount = 0;
        try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            for (String line : (Iterable<String>) lines::iterator) {
                T item = parse(line);
                list.add(item);
                readCount++;
            }
            return readCount;
        }
    }

    protected abstract T parse(String line);

}
