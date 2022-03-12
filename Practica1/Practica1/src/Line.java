
/**
 *
 * @author meritxellfont
 */
public class Line {
    private int cursor;
    private StringBuilder stringLine;
    private boolean insert;

    public Line() {
        this.stringLine = new StringBuilder();
    }
    
    public void addChar(char c){
        this.stringLine.insert(this.cursor,c);
        this.cursor++;            
    }
    public void home(){
        this.cursor=0;
    }
    public void end(){
        this.cursor=this.stringLine.length();
    }
    public void delete(){
        if(this.cursor < this.stringLine.length()){     //mirar a veure si hi ha prous caracters per esborrar --> SUPR
            this.stringLine.deleteCharAt(this.cursor);
        }
    }
    public void moveCursor(int move){
        if(this.cursor + move >= 0 && cursor + move <=this.stringLine.length()){
            this.cursor+=move;
        }        
    }
    public void backspace(){    //DELETE  <--
        if(cursor != 0){
            this.stringLine.deleteCharAt(this.cursor-1);
            this.cursor--;
        }
    }
    public void insert(){
        this.insert = !insert;
    }
    public String displayString(){
        StringBuilder disp = new StringBuilder();
        disp.append('\r');
        disp.append(this.stringLine.toString());
        disp.append(" ");
        disp.append("\033[");//serveix per moure el cursor?
        disp.append(1+this.stringLine.length());
        disp.append("D");
        return disp.toString();
    }
    public String toString(){
        return this.stringLine.toString();
    }    
}
