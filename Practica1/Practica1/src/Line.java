
/**
 *
 * @author meritxellfont
 */
public class Line {
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
        if (this.cursor < this.linia.length()) {
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
        }
    }

    @Override
    public String toString() {
        return this.linia.toString();
    }  
}
