package memorizingtool.file;

public class FileReaderWords extends FileReaderBase<String> {

    @Override
    protected String parse(String line) {
        return line;
    }

}
