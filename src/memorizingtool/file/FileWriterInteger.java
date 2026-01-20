package memorizingtool.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//peculiar shimmering object hidden behind a cluster of bushes. Intrigued, she cautiously...
public class FileWriterInteger implements CustomFileWriter<Integer> {

    public void write(String fileName, ArrayList<Integer> data) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Integer i : data) {
            printWriter.println(i);
        }
        printWriter.close();
    }

}