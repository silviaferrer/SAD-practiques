/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatswing;

/**
 *
 * @author Meritxell <meritxell.org>
 */
import java.io.*;
import java.net.*;


public class MyServerSocket extends ServerSocket {
    private ServerSocket ss;
    private MySocket ms;
    public MyServerSocket(int port) throws IOException{
        this.ss = new ServerSocket(port);
    }

    /**
     * ServerSoket es mante escoltant, rep una sol·licitud, crea un Socket en un
     * port que no estigui utiltizant-se i estableix connexió entre client i 
     * servidor. SS mai és ell el que transmet.
     * @return 
     */
    @Override
    public MySocket accept(){
        try {
            this.ms = new MySocket(this.ss.accept());                                          
            return this.ms;
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return  null;
    }

    
    /**
     * Tanca connexió, elimina tots els processos que estiguin en actiu.
     */
    @Override
    public void close(){
        try {
            this.ss.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
  
}

