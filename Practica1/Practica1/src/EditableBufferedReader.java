
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author meritxellfont
 */
public class EditableBufferedReader extends BufferedReader {

    private Line line;

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
    public int read() throws IOException {
        while (true) {
            int tecla = super.read();
            switch (tecla) {
                case 127:
                    this.line.backspace();
                    System.out.print(this.line.displayString());
                    break;
                case 70:
                    this.line.end();
                    System.out.print(this.line.displayString());
                    break;
                case 126:
                    this.line.delete();
                    System.out.print(this.line.displayString());
                    break;
                case 65:
                    this.line.backspace();
                    System.out.print(this.line.displayString());
                    break;
                case 72:
                    this.line.home();
                    System.out.print(this.line.displayString());
                    break;
                case 27:
                    switch (super.read()) {
                        case 91:
                            switch (super.read()) {
                                case 68:
                                    this.line.moveCursor(-1);
                                    System.out.print(this.line.displayString());
                                    break;
                                case 67:
                                    this.line.moveCursor(1);
                                    System.out.print(this.line.displayString());
                                    break;
                            }
                            break;
                    }
                    break;
                default:
                    return tecla;

            }
        }

    }

    @Override
    public String readLine() throws IOException {
        setRaw();
        int tecla = 0;
        while ((tecla = read()) != 13) {//si es diferent a enter
            this.line.addChar((char) tecla);
            System.out.print(this.line.displayString());
            //System.out.println(inChar);
        }
        unsetRaw();
        return this.line.toString();
    }


}
