package memorizingtool.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWriterWords implements CustomFileWriter<String> {

    public void write(String fileName, ArrayList<String> data) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (String i : data) {
            printWriter.println(i);
        }
        printWriter.close();
    }

}