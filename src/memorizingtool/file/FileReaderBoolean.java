package memorizingtool.file;

public class FileReaderBoolean extends FileReaderBase<Boolean> {

    @Override
    protected Boolean parse(String line) {
        return Boolean.parseBoolean(line);
    }

}
