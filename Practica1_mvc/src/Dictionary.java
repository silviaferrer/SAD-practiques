/**
 *
 * @author meritxellfont
 */
public class Dictionary {

      public final static int CRTL_C = 3;
    public final static int ENTER = 13;//^M
    public final static int ESC = 27;//^[
    public final static int INSERT = 50;//^[[2~
    public final static int DELETE = 51;//^H
    public final static int UP = 65;
    public final static int DOWN = 66;
    public final static int RIGHT = 67;//^[[C
    public final static int LEFT = 68;//^[[D
    public final static int END = 70;//^[[F
    public final static int HOME = 72;//^[[H
    public final static int CLAUDATOR = 91;
    public final static int WAVE = 126;
    public final static int BACKSPACE = 127;//^[[3~

    public final static String DEL_s = "\033[1P"; 
    public final static String HOME_s = "\033[1G";
    public final static String LEFT_s = "\033[1D";
    public final static String RIGHT_s = "\033[1C";
    public final static String INSERT_s = "\033[1@"; 
    public final static String END_s = "\033[";//S'afegeix una c després de la posició final
}
