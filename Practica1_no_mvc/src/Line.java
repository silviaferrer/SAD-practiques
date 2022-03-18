
/**
 *
 * @author meritxellfont
 */
public class Line {
//CORRECTE

    private StringBuilder linia;
    private int cursor;
    private boolean ins;//TRUE: insertChar... FALSE: setChar sobreescriptura

    public StringBuilder getLinia() {
        return linia;
    }

    public Line() {
        this.linia = new StringBuilder();
        this.cursor = 0;
        this.ins = false;
    }

    public void addChar(char c) {
        if (this.ins == true) {
            //insertar
            this.linia.setCharAt(cursor, c);
            this.cursor++;
        } else {
            //no insertar
            this.linia.insert(cursor, c);
            this.cursor++;
        }
    }

    public void insert() {
        if (this.ins == true) {
            this.ins = false;
        } else {
            this.ins = true;
        }
    }

    public void delete() {//suprimir
        if (this.cursor < this.linia.length()-2) {
            this.linia.deleteCharAt(this.cursor);
            
        }
    }

    public void move(int i) {//rigth ==-1 ; left == 1
        if (i > 0 && this.cursor != this.linia.length()) {
            this.cursor += 1;
        } else if (i < 0 && this.cursor != 0) {
            this.cursor -= 1;
        }
    }

    public void end() {
        this.cursor = this.linia.length();
    }

    public void home() {
        this.cursor = 0;
    }

    public void backspace() {
        if (this.cursor != 0) {
            this.linia.deleteCharAt(this.cursor - 1);
            this.cursor--;
        }
    }

    @Override
    public String toString() {
        return this.linia.toString();
    }
   
    public String displayString() {
        StringBuilder disp = new StringBuilder();
        disp.append('\r');
        disp.append(this.linia.toString());
        disp.append(" ");
        disp.append("\033[");
        disp.append(1 + this.linia.length());
        disp.append("D");
        return disp.toString();
    }
 /*
    private int cursor;
    private StringBuilder stringLine;
    private boolean insert;

    public Line() {
        this.stringLine = new StringBuilder();
    }

    public void addChar(char c) {
        this.stringLine.insert(this.cursor, c);
        this.cursor++;
    }

    public void home() {
        this.cursor = 0;
    }

    public void end() {
        this.cursor = this.stringLine.length();
    }

    public void delete() {
        if (this.cursor < this.stringLine.length()) {     //mirar a veure si hi ha prous caracters per esborrar --> SUPR
            this.stringLine.deleteCharAt(this.cursor);
        }
    }

    public void moveCursor(int move) {
        if (this.cursor + move >= 0 && cursor + move <= this.stringLine.length()) {
            this.cursor += move;
        }
    }

    public void backspace() {    //DELETE  <--
        if (cursor != 0) {
            this.stringLine.deleteCharAt(this.cursor - 1);
            this.cursor--;
        }
    }

    public void insert() {
        this.insert = !insert;
    }

    public String displayString() {
        StringBuilder disp = new StringBuilder();
        disp.append('\r');
        disp.append(this.stringLine.toString());
        disp.append(" ");
        disp.append("\033[");//serveix per moure el cursor?
        disp.append(1 + this.stringLine.length());
        disp.append("D");
        return disp.toString();
    }

    public String toString() {
        return this.stringLine.toString();
    }*/
}
