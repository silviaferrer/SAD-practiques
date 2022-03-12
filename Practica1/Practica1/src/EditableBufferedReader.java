/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica13;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author meritxellfont
 */
public class EditableBufferedReader extends BufferedReader {

    public final static int CRTL_C = 3;
    public final static int ENTER = 13;
    public final static int ESC = 27;
    public final static int INSERT = 50;
    public final static int SUPR = 51;
    public final static int UP = 65;
    public final static int DOWN = 66;
    public final static int RIGHT = 67;
    public final static int LEFT = 68;
    public final static int END = 70;
    public final static int HOME = 72;
    public final static int CLAUDATOR = 91;
    public final static int WAVE = 126;
    public final static int DELETE = 127;

    private final Line line;
    
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

    /**
     * llegeix el següent caràcter o la següent tecla de cursor
     * @return 
     */
    @Override
    public int read() {
        int tecla;
        try {
            tecla = super.read();

            if (tecla != ESC) {
                return tecla;
            }
            tecla = super.read();
            if (tecla == CLAUDATOR) {
                tecla = super.read();
                return (tecla * (-1));  //per distingir al mètode readLine() que aquest és un caràcter especial per fer una acció
            }

        } catch (IOException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;   //retornem un nullChar
    }

    public Line readLinea() {
        this.setRaw();
        int tecla = this.read();
        while (tecla != ENTER || tecla != ESC || tecla!=CTRL_C) {
            if (tecla > 0) {
                //caracter "normal"
                line.addChar((char) tecla);
            } else if (tecla < 0) {
                //caracter "especial"
                tecla = tecla * (-1);
                switch (tecla) {
                    case INSERT:                        
                        line.insert();
                        break;
                    case SUPR:
                        line.supr();
                        break;
                    case UP:
                        //line.move(-2);
                        break;
                    case DOWN:
                        //line.move(2);
                        break;
                    case RIGHT:
                        line.move(-1);
                        break;
                    case LEFT:
                        line.move(1);
                        break;
                    case END:
                        line.end();
                        break;
                    case HOME:
                        line.home();
                        break;
                    case DELETE:
                        line.delete();
                        break;
                    default:
                    //res
                }
            } else {
                //error
                System.out.println("Error lectura, tecla == 0");
            }
        }
        System.out.print(this.line);
        this.unsetRaw();
        return this.line;
    }
}


/**
 *   
    public final static int INSERT = 50;
    public final static int SUPR = 51;
    public final static int UP = 65;
    public final static int DOWN = 66;
    public final static int RIGHT = 67;
    public final static int LEFT = 68;
    public final static int END = 70;
    public final static int HOME = 72;
    public final static int DELETE = 127;
 */
