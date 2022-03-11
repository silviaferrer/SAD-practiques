
/**
 *
 * @author meritxellfont
 */
public class Line {
        private int pos;
    private StringBuilder linia;
    private boolean ins;

    public Line() {
        this.linia = new StringBuilder();
    }

    public void end() {
        this.pos = this.linia.length();
    }

    public void delete() {
        if (this.pos < this.linia.length() - 2) {//mirar a veure si hi ha prous caracters per esborrar
            this.linia.deleteCharAt(this.pos);
        }
    }

    public void addChar(char c) {
        this.linia.insert(this.pos, c);
        this.pos++;
    }

    public void home() {
        this.pos = 0;
    }

    public void backspace() {
        if (pos != 0) {
            this.linia.deleteCharAt(this.pos - 1);
            this.pos--;
        }
    }

    public void moveCursor(int move) {
        if (this.pos + move >= 0 && pos + move <= this.linia.length()) {
            this.pos += move;
        }
    }

    public void insert() {
        this.ins = !ins;
    }

    public String displayString() {
        StringBuilder disp = new StringBuilder();
        disp.append('\r');
        disp.append(this.linia.toString());
        disp.append(" ");
        disp.append("\033[");//serveix per moure el cursor?
        disp.append(1 + this.linia.length());
        disp.append("D");
        return disp.toString();
    }

    public String toString() {
        return this.linia.toString();
    }    
}
