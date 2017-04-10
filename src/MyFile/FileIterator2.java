package MyFile;

import java.io.IOException;
import java.util.Iterator;

public class FileIterator2 implements Iterable<String>, AutoCloseable {
    private FileIterator fi;

    public FileIterator2(String filename) throws IOException {
        fi = new FileIterator(filename);
    }

    @Override
    public void close() throws Exception {
        fi.close();
    }

    @Override
    public Iterator<String> iterator() {
        return fi;
    }
}
