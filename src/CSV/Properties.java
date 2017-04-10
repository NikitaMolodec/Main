package CSV;


public class Properties {

    private static final String DEFAULT_DELIMITER = ",";
    private static final boolean DEFAULT_HAS_HEADER = false;

    String delimiter;
    boolean isHeader;

    public Properties(String delimiter, boolean isHeader){
        this.delimiter = delimiter;
        this.isHeader = isHeader;
    }

    public Properties(){
        this(DEFAULT_DELIMITER, DEFAULT_HAS_HEADER);
    }

    public Properties(String delimiter){
        this(delimiter,DEFAULT_HAS_HEADER);
    }

    public Properties(boolean isHeader){
        this(DEFAULT_DELIMITER, isHeader);
    }
}