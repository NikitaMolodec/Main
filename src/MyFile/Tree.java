package MyFile;

import java.io.File;

public class Tree {

    private static void getTree(String filename, int glubina) throws NullPointerException {
        File file = new File(filename);
        File[] files = file.listFiles();
        for(File element : files){
            if(element.isFile()){
                for(int i = 0; i < glubina; i++){
                    System.out.print("-");
                }
                System.out.println(element.getName());
            }
            else{
                for(int i = 0; i < glubina; i++){
                    System.out.print("-");
                }
                System.out.println(element.getName());
                getTree(element.getAbsolutePath(), glubina + 1);
            }
        }
    }

    public static void getTree(String filename) throws NullPointerException{
        getTree(filename, 0);
    }

    private static void getFiles(String filename) throws NullPointerException{
        File file = new File(filename);
        File[] files = file.listFiles();
        for(File element : files){
            if(element.isFile()){
                System.out.println(element.getName());
            }
        }
    }

    private static void getDirectorys(String filename)throws NullPointerException{
        File file = new File(filename);
        File[] files = file.listFiles();
        for(File element : files){
            if(element.isDirectory()){
                System.out.println(element.getName());
            }
        }
    }

}
