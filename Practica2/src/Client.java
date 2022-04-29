/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author meritxellfont
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client {

    public static final int sPort = 5000;
    public static final String sHost = "localhost";
    

    public static void main(String[] args) {

        final MySocket clientSocket; // socket used by client to send and recieve data from server
        final BufferedReader br;   // object to read data from socket
        final PrintWriter pw;     // object to write data into socket
        final Scanner sc = new Scanner(System.in); // object to read data from user's keybord
        clientSocket = new MySocket(sHost, sPort);
        pw = new PrintWriter(clientSocket.myGetOutputStream());
        br = new BufferedReader(new InputStreamReader(clientSocket.myGetInputStream()));
        
        Thread sender= new Thread(new Runnable() {
                String msg=""; //variable that will contains the data writter by the user
                @Override   // annotation to override the run method
                public void run() {
                    while(!msg.equals("exit")){
                        msg = sc.nextLine(); //reads data from user's keybord
                        pw.println(msg);    // write data stored in msg in the clientSocket
                        pw.flush();   // forces the sending of the data
                    }
                    //pw.close();
                    //sc.close();
                    
                }
            });
        sender.start();
        
        Thread receiver = new Thread(new Runnable() {
            String msg;
            
            @Override
            public void run() {
                try {
                    msg = br.readLine();
                    while (msg != null) {
                        System.out.println(msg);
                        msg = br.readLine();
                    }
                    System.out.println("Has sortit del chat");
                    pw.close();
                    clientSocket.myClose();
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        });
        receiver.start();
    }
}
