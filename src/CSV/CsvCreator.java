package CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CsvCreator {

    public String filename;
    public Properties properties;
    public List<String> header = new ArrayList<>();
    private static final Properties DEFAULT_PROPERTIES = new Properties();
    private static final int MAX_VALUE = 100;
    private Random random = new Random();

    public CsvCreator(String filename, Properties properties){
        this.filename = filename;
        this.properties = properties;
    }

    public CsvCreator(String filename){
        this(filename, DEFAULT_PROPERTIES);
    }

    //создает файл типа CSV с заданным количеством строк и частотой времени
    public void createFile(int chast, int length){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(filename));){
            if (properties.isHeader) {
                header.add("Time");
                header.add("Random");
                br.write(listToStr(header) + "\n");
            }
            for(int i = 0; i < length; i++){
                br.write(i * chast + properties.delimiter + random.nextInt(MAX_VALUE) + "\n");
            }
        } catch (IOException e) {
            throw  new IllegalStateException("Can't write in file");
        }
    }

    //listToStr преобразует список строк в строку CSV файла
    private String listToStr(List<String> list){
        String result = "";
        for(int i = 0; i < list.size() - 1; i++){
            result += list.get(i) + properties.delimiter;
        }
        result += list.get(list.size() - 1);
        return result;
    }
}
