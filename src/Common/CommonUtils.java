package Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final String DELIMITER = " ";

    //hasNumber принимает строку и проверяет на наличие цифр
    public static boolean hasNumbers(String text) {
        Pattern pat=Pattern.compile("[0-9]");
        Matcher matcher=pat.matcher(text);
        return matcher.find();
    }

    //getNumbers принимает строку и возвращает строку содержащую все цифры входной строки
    public static String getNumbers(String text){
        StringBuilder result = new StringBuilder();
        Pattern pat=Pattern.compile("[0-9]");
        Matcher matcher=pat.matcher(text);
        while (matcher.find()) {
            result.append(matcher.group());
            result.append(DELIMITER);
        }
        return result.toString();
    }

    //printExceptions принимает на вход исключение e и создает строку ошибок принятых из этого исключения
    public static String stackTraceToString (RuntimeException e){
        StackTraceElement[] strings = e.getStackTrace();
        StringBuilder result = new StringBuilder();
        result.append(e.getMessage()).append("\n");
        for(StackTraceElement element : strings){
            result.append(element.toString()).append("\n");
        }
        return result.toString();
    }
}
