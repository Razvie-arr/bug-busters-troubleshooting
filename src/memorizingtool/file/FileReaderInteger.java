package memorizingtool.file;

public class FileReaderInteger extends FileReaderBase<Integer> {

    @Override
    protected Integer parse(String line) {
        return Integer.parseInt(line);
    }

}