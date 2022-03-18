
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
    private Console console;

    public EditableBufferedReader(Reader in) {
        super(in);
        this.line = new Line();
        this.console = new Console();
        this.line.addObserver(this.console);
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

                    case 127:
                        line.backspace();
                        System.out.print(line.displayString());
                        break;
                    case 126:
                        line.delete();
                        System.out.print(line.displayString());
                        break;
                    case 27:
                        switch (super.read()) {
                            case 91:
                                switch (super.read()) {
                                    case 72:
                                        this.line.home();
                                        System.out.print(this.line.displayString());
                                        break;
                                    case 70:
                                        this.line.end();
                                        System.out.print(this.line.displayString());
                                        break;
                                    case 50:
                                        this.line.insert();
                                        System.out.print(this.line.displayString());
                                        break;
                                    case 67:
                                        line.move(1);
                                        System.out.print(line.displayString());
                                        break;
                                    case 68:
                                        line.move(-1);
                                        System.out.print(line.displayString());
                                        break;
                                    /*case 777:
                                        line.delete();
                                        System.out.print(line.displayString());
                                        break;*/
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
        int c = 0;
        while ((c = this.read()) != Dictionary.ENTER) {//si es diferent a enter
            this.line.addChar((char) c);
            System.out.print(this.line.displayString());
        }
        unsetRaw();
        return this.line.toString();
    }
}