package MyFile;

import java.io.File;
import java.nio.file.FileSystemNotFoundException;

public class TreeUtils {

    private static final String DEFAULT_INDENT = "-";

    private static void printFileName(File file, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print(DEFAULT_INDENT);
        }
        System.out.println(file.getName());
    }

    private static File[] getContent(String filename){
        File file = new File(filename);
        if(!file.exists()){
            throw new FileSystemNotFoundException("can't found file");
        }
        File[] files = file.listFiles();
        if(files == null){
            throw new NullPointerException();
        }
        return files;
    }

    public static void printTree(String filename){
        printTree(filename, 0);
    }

    private static void printTree(String filename, int depth){
        File[] files = getContent(filename);
        for(File element : files){
            if(element.isFile()){
                printFileName(element, depth);
            }
            else{
                printFileName(element, depth);
                printTree(element.getAbsolutePath(), depth + 1);
            }
        }
    }

    public static void printFiles(String filename){
        File[] files = getContent(filename);
        for(File element : files){
            if(element.isFile()){
                System.out.println(element.getName());
            }
        }
    }

    public static void printDirectories(String filename){
        File[] files = getContent(filename);
        for(File element : files){
            if(element.isDirectory()){
                System.out.println(element.getName());
            }
        }
    }

}
