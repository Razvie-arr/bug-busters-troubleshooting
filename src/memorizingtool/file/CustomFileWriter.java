package memorizingtool.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CustomFileWriter<T> {

    public void write(String fileName, List<T> data) throws IOException {
        try (FileWriter customFileWriter = new FileWriter(fileName)) {
            PrintWriter printWriter = new PrintWriter(customFileWriter);
            for (T item : data) {
                printWriter.println(item);
            }
            printWriter.close();
        }
    }

}
