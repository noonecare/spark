import java.io.File;
import java.net.URL;

/**
 * 打包前后执行这个程序
 * java -cp .
 */
public class PrintResourcesFile {
    // 返回资源文件的路径
    private String printPathInAJar(String fileName) {
        return this.getClass().getClassLoader().getResource(fileName).getPath();
    }

    // 返回资源文件的 URL
    private String printURLInAJar(String fileName) {
        URL fileURL = this.getClass().getClassLoader().getResource(fileName);
        System.out.println(fileURL.toString());
        return fileURL.toString();
    }

    public static void main(String[] args) {

        PrintResourcesFile printResourcesFile = new PrintResourcesFile();

        for (String arg :args) {
            printResourcesFile.printPathInAJar(arg);
            String path = new PrintResourcesFile().printPathInAJar(arg);
            String url = new PrintResourcesFile().printURLInAJar(arg);
            System.out.println(new File(path).exists());
            System.out.println(new File(url).exists());
        }
    }
}
