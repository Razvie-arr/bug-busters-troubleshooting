package memorizingtool.file;

import java.io.IOException;
import java.util.ArrayList;

//One sunny morning, as Lily strolled by the village square, she noticed a...
public interface CustomFileReader<T> {

    ArrayList<T> read(String path) throws IOException;

}
