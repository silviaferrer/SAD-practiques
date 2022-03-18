
import java.util.Observable;

/**
 *
 * @author meritxellfont
 */
public class Line extends Observable {

    private int cursor;
    private StringBuilder stringLine;
    private boolean ins;
    private String event;

    public Line() {
        this.stringLine = new StringBuilder();
        this.cursor = 0;
        this.ins = false;
        this.event = "";
    }

    public void addChar(char c) {
        if (this.ins == true) {
            //insertar
            this.stringLine.setCharAt(cursor, c);
            this.cursor++;
            this.event = String.valueOf(c);

        } else {
            //no insertar
            this.stringLine.insert(cursor, c);
            this.cursor++;
            this.event = Dictionary.INSERT_s + c;
        }
        super.setChanged();//<-- mvc
        super.notifyObservers(this.event);//<--mvc
    }

    public void home() {
        this.cursor = 0;
        this.event = Dictionary.HOME_s;
        super.setChanged();
        super.notifyObservers(this.event);
    }

    public void end() {
        this.cursor = this.stringLine.length();
        this.event = Dictionary.END_s + this.cursor + 'C';
        super.setChanged();
        super.notifyObservers(this.event);
    }

    public void delete() {//suprimir
        if (this.cursor < this.stringLine.length()) {     //mirar a veure si hi ha prous caracters per esborrar --> SUPR
            this.stringLine.deleteCharAt(this.cursor);
            this.event = Dictionary.DEL_s;
            super.setChanged();
            super.notifyObservers(this.event);
        }
    }

    public void move(int i) {//rigth ==-1 ; left == 1
        if (i > 0 && this.cursor != this.stringLine.length()) {
            this.cursor += 1;
            this.event = Dictionary.LEFT_s;
        } else if (i < 0 && this.cursor != 0) {
            this.cursor -= 1;
            this.event = Dictionary.RIGHT_s;
        }
        super.setChanged();
        super.notifyObservers(this.event);
    }

    public void backspace() {    //DELETE  <--
        if (cursor != 0) {
            this.stringLine.deleteCharAt(this.cursor - 1);
            this.cursor--;
        }
    }

    public void insert() {
        this.ins = !ins;
    }

    public String displayString() {
        StringBuilder disp = new StringBuilder();
        disp.append('\r');
        disp.append(this.stringLine.toString());
        disp.append(" ");
        disp.append("\033[");
        disp.append(1 + this.stringLine.length());
        disp.append("D");
        return disp.toString();
    }

    public String toString() {
        return this.stringLine.toString();
    }
}
