package CSV;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvParser {

    //путь к CSV файлу
    private String path;
    //header
    private List<String> header  = new ArrayList<>();
    //Настройки файла
    private Properties properties;
    //CSV файл в виде списка
    private List<List<String>> matrix = new ArrayList<>();
    //количество новых строк
    int countColumn = 0;

    private static final Properties DEFAULT_PROPERTIES = new Properties();

    public CsvParser(String path, Properties properties){
        this.path = path;
        this.properties = properties;
        readAll();
    }

    public CsvParser(String path){
        this(path, DEFAULT_PROPERTIES);
    }

    public CsvParser(CsvCreator csv){
        this(csv.filename, csv.properties);
        header = csv.header;
    }

    //countLine подсчитывает количество строк в CSV файле
    private int countLine(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            int counter = 0;
            while(br.readLine() != null){
                counter++;
            }
            return counter;
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }

    //readAll читает весь файл в список
    private List<List<String>> readAll(){

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            if(properties.isHeader) {
                Collections.addAll(header, br.readLine().split(properties.delimiter));
                for (int i = 1; i < countLine(); i++) {
                    List<String> list = new ArrayList<>();
                    Collections.addAll(list, br.readLine().split(properties.delimiter));
                    matrix.add(list);
                }
            }else{
                for (int i = 0; i < countLine(); i++) {
                    List<String> list = new ArrayList<>();
                    Collections.addAll(list, br.readLine().split(properties.delimiter));
                    matrix.add(list);
                }
            }
            return matrix;
        } catch (IOException e) {
            throw new IllegalStateException("Can't read file");
        }
    }

    //getRaw возвращает строку под номером raw из файла в виде списка
    public List<String> getRaw(int raw){return matrix.get(raw - 1);}

    //getColumn возвращает столбец под номером column из файла в виде списка
    public List<String> getColumn(int column){
        List<String> result = new ArrayList<>();
        for(List<String> line : matrix){
            result.add(line.get(column - 1));
        }
        return result;
    }

    //getValue возвращает элемент CSV файла из строки и столбца под номерами raw и column соответсвенно
    public String getValue(int raw, int column){return matrix.get(raw - 1).get(column - 1);}

    //getHeader возвращает header в виде списка
    public List<String> getHeader(){return header;}

    //setHeader переводит строку line из CSV стиля в список и присваевает его  полю header
    public void setHeader(String line){
        header.clear();
        Collections.addAll(header, line.split(properties.delimiter));
        properties.isHeader = true;
    }

    //setValue записывает значение в указанное место
    public void setValue(String value, int raw, int column){
        matrix.get(raw - 1).remove(column - 1);
        matrix.get(raw - 1).add(column - 1, value);
    }

    //addRaw добавляет строку raw в указонное место index
    public void addRaw(List<String> raw, int index){
        matrix.add(index - 1, raw);
    }

    public void addRaw(List<String> raw){
        addRaw(raw, matrix.size() + 1);
    }

    //addColumn добавляет столбец column в указонное место index
    public void addColumn(List<String> column, int index){
        for(int i = 0; i < matrix.size(); i++){
            matrix.get(i).add(index - 1, column.get(i));
        }
        if(properties.isHeader){
            header.add("newColumn" + ++countColumn);
        }
    }

    public void addColumn(List<String> column){
        addColumn(column, matrix.get(matrix.size() - 1).size() + 1);
    }

    //создает CSV файл с адресом path
    public void toFile(String path){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            if(properties.isHeader){
                for(int i = 0; i < header.size() - 1; i++){
                    br.write(header.get(i) + properties.delimiter);
                }
                br.write(header.get(header.size() - 1) + "\n");
            }
            for(List<String> line : matrix){
                for(int i = 0; i < line.size() - 1; i++){
                    br.write(line.get(i) + properties.delimiter);
                }
                br.write(line.get(line.size() - 1) + "\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't write in file");
        }
    }

    //печатает содержимое CSV файла
    public void printAll(){
        if(properties.isHeader){
            for(String note : header){
                System.out.print(note + " ");
            }
            System.out.println();
        }
        for(List<String> line : matrix){
            for(String note : line){
                System.out.print(note + " ");
            }
            System.out.println();
        }
    }

    //plavSr получает list и возвращает лист содержащий плавающее среднее значений list
    public List<String> plavSr(int n, List<String> list){
        List<String> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(((i - n) >= 0) && ((i + n) < list.size())){
                double sr = 0;
                for(int j = i - n; j <= i + n; j++){
                    sr += Double.parseDouble(list.get(j));
                }
                sr /= 2 * n + 1;
                result.add(i, "" + sr);
            }
            else{
                result.add(i, "");
            }

        }
        return result;
    }


}
