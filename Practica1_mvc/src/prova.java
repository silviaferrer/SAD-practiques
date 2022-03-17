
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class prova {

    public static void main(String[] args) {
        // Read the stream 'demo.txt'
        // containing text "GEEKSFORGEEKS"
        try {
            // Convert fileReader to
            // bufferedReader
            try (FileReader fileReader = new FileReader("/home/meritxellfont/Escriptori/Meritxell/SAD/demo.txt")) {
                // Convert fileReader to
                // bufferedReader
                BufferedReader buffReader = new BufferedReader(fileReader);
                
                while (buffReader.ready()) {
                    // Read and print characters one by one
                    // by converting into character
                    int aux = buffReader.read();
                    System.out.println("Char :" + (char) aux + "  Int:" + aux);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No s'ha trobat el fitxer");
        } catch (IOException ex) {
            System.out.println("Error");
        }
        
    }
}
