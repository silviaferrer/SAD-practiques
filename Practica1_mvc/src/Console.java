
import java.util.Observable;
import java.util.Observer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
