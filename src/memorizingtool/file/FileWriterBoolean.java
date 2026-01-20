package memorizingtool.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//gleamed in the sunlight, as if beckoning her to uncover its secrets.
public class FileWriterBoolean implements CustomFileWriter<Boolean> {

    public void write(String fileName, ArrayList<Boolean> data) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Boolean i : data) {
            printWriter.println(i);
        }
        printWriter.close();
    }

}