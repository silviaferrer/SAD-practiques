

import java.net.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MySocket extends Socket {

    private Socket ms;

    public MySocket(String host, int port) {

        try {
            this.ms = new Socket(host, port);   
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public MySocket(Socket socket) {
        this.ms = socket;
    }

    //utilitzem mètodes heredats de la classe Socket amb tractament d'errors
    
    /*
    connecta el socket al server, no hi posem timeout
    */
    public void myConnect(SocketAddress endpoint) {

        try {
            this.ms.connect(endpoint); 
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /*
    llegeix el input stream del socket associat
    si es tanca el stream retornat, es tanca el socket associat
    */    
    public InputStream myGetInputStream() {
        try {
            return ms.getInputStream(); 
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;

    }
    /*
    escriu el output stream del socket associat i també t'ho retorna
    si es tanca el stream retornat, es tanca el socket associat
    */
    public OutputStream myGetOutputStream() {

        try {
            return ms.getOutputStream(); 
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }

    //@Override
    /*
    tanca el socket
    quan es tanca, el input i output també es tanquen
    */
    public void myClose() {
        try {
            ms.close(); 
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}






