import CSV.*;
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties(";", true);
        CsvCreator csvCreator = new CsvCreator("src/test.txt", properties);
        csvCreator.createFile(2, 10);
        CsvParser csvParser = new CsvParser(csvCreator);
        csvParser.addColumn(csvParser.plavSr(3, csvParser.getColumn(2)));
        csvParser.toFile(csvCreator.filename);
    }
}
