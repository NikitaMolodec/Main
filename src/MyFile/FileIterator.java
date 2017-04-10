package MyFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class FileIterator implements Iterator<String>, AutoCloseable{

    private String filename;
    private final BufferedReader br;
    private String currentLine;

    public FileIterator(String filename) throws IOException {
        this.filename = filename;
        br = new BufferedReader(new FileReader(filename));
        currentLine = br.readLine();

    }

    @Override
    public boolean hasNext() throws NoSuchElementException{
        return Objects.nonNull(currentLine);
    }

    @Override
    public String next() throws NoSuchElementException{
        try {
            String line = currentLine;
            currentLine = br.readLine();
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        br.close();
    }
}
