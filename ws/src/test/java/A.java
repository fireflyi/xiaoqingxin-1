import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author by lishihao
 * @date 2019/10/19
 * DESC TODO
 */
public class A {


    public static void main(String[] args) throws IOException {

        File file =new File("./2.txt");
        Writer out =new FileWriter(file);
        String data="888";
        out.write(data);
        out.close();
    }

}
