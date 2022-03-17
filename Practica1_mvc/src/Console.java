
import java.util.Observable;
import java.util.Observer;


/**
 *
 * @author meritxellfont
 */
public class Console implements Observer{
    
    @Override
    public void update(Observable o, Object arg){
		String event = (String) arg;
		
		if(!"".equals(event) && o != null){
			System.out.print(event);
		}
	}
}
