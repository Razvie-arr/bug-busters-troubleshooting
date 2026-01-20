package memorizingtool.file;

import java.io.IOException;
import java.util.ArrayList;

public interface CustomFileWriter<T> {

    void write(String fileName, ArrayList<T> data) throws IOException;

}
