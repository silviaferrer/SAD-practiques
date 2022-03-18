
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author meritxellfont
 */
public class EditableBufferedReader extends BufferedReader {

    private Line line;

    public final static int CRTL_C = 3;
    public final static int ENTER = 13;//^M
    public final static int ESC = 27;//^[
    public final static int INSERT = 50;//^[[2~ --> ESC + 91 + 50
    public final static int DELETE = 51;//^H -->
    public final static int UP = 65;
    public final static int DOWN = 66;
    public final static int RIGHT = 67;//^[[C
    public final static int LEFT = 68;//^[[D
    public final static int END = 70;//^[[F
    public final static int HOME = 72;//^[[H
    public final static int CLAUDATOR = 91;
    public final static int WAVE = 126;
    public final static int BACKSPACE = 127;//^[[3~

    public EditableBufferedReader(Reader in) {
        super(in);
        this.line = new Line();
    }

    private void setRaw() {
        String[] command = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void unsetRaw() {
        String[] command = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int read() {
        try {
            while (true) {
                int tecla = super.read();
                switch (tecla) {

                    case BACKSPACE:
                        line.backspace();
                        System.out.print(line.displayString());
                        break;
                    case CLAUDATOR:
                        if (super.read() == 51) {
                            line.delete();
                            System.out.print(line.displayString());

                        }
                        break;
                    case ESC:
                        switch (super.read()) {
                            case CLAUDATOR:
                                switch (super.read()) {
                                    case HOME:
                                        this.line.home();
                                        System.out.print(this.line.displayString());
                                        break;
                                    case END:
                                        this.line.end();
                                        System.out.print(this.line.displayString());
                                        break;
                                    case INSERT:
                                        this.line.insert();
                                        System.out.print(this.line.displayString());
                                        break;
                                    case RIGHT:
                                        line.move(1);
                                        System.out.print(line.displayString());
                                        break;
                                    case LEFT:
                                        line.move(-1);
                                        System.out.print(line.displayString());
                                        break;
                                    case DELETE:
                                        line.delete();
                                        System.out.print(line.displayString());
                                        break;
                                }
                                break;
                        }
                        break;
                    default:
                        return tecla;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public String readLine() throws IOException {
        setRaw();
        int inChar = 0;
        while ((inChar = read()) != 13) {
            line.addChar((char) inChar);
            System.out.print(line.displayString());
        }
        unsetRaw();
        return line.toString();

    }

}
