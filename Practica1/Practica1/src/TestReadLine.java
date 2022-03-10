
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author meritxellfont
 */
public class TestReadLine {
    public static void main(String[] args){
        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        String str = null;
        try{
            str = in.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("\nline is: " + str);
    }
}
