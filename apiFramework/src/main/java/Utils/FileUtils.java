package Utils;

public class FileUtils {

    public static String getFilePath(String fileName){
        return Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
    }
}
