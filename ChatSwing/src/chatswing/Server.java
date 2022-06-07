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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Server {

    public static HashMap<String, User> clients = new HashMap<>();
    private MyServerSocket serverSocket;
    public static final int sPort = 5000;

    public static void main(String[] args) {
        final MyServerSocket serverSocket;

        try {
            serverSocket = new MyServerSocket(sPort);
            while (true) {
                new Thread(new User(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static class User implements Runnable {

        private final MySocket socket;
        private String lastMsg = "";
        private String clientName = "";
        public BufferedReader br = null;
        public PrintWriter pw = null;

        public User(MySocket ms) {
            this.socket = ms;
            this.br = new BufferedReader(new InputStreamReader(this.socket.myGetInputStream()));
            this.pw = new PrintWriter(this.socket.myGetOutputStream(), true);
        }

        @Override
        public void run() {
            boolean sortir = false;

            while (this.clientName.equals("")) {
                this.pw.println("Introdueix el teu Usuari: ");
                this.pw.flush();
                try {
                    this.clientName = this.br.readLine();
                } catch (IOException e) {
                    System.out.println(e);
                }

                if (clients.containsKey(this.clientName)) {
                    this.pw.println("Usuari ocupat");
                    this.pw.flush();
                    this.clientName = "";
                } else {
                    for (User u : clients.values()) {
                        u.pw.print("\t\t>>" + this.clientName + " s'ha unit en el chat<<\n");
                        u.pw.flush();
                    }

                    System.out.println("Nou usuari: " + this.clientName);
                    clients.put(this.clientName, this);
                }
            }

            while (!sortir) {
                try {
                    if (this.br.ready()) {
                        this.lastMsg = this.br.readLine();
                        System.out.println(this.clientName + ": '" + this.lastMsg + "'");
                        if (this.lastMsg.equals("exit")) {
                            clients.remove(this.clientName);
                            for (User u : clients.values()) {
                                if (!u.clientName.equals(this.clientName)) {
                                    u.pw.print("\t\t>>" + this.clientName + " ha sortit del chat\n");
                                    u.pw.flush();
                                }
                            }
                            System.out.println(this.clientName + " ha sortit del chat");
                            sortir = true;
                        } else if (!this.lastMsg.equals("")) {
                            for (User u : clients.values()) {
                                if (!u.clientName.equals(this.clientName)) {
                                    u.pw.print("\t\t>>" + this.clientName + ": " + this.lastMsg + " \n");
                                    u.pw.flush();
                                }
                            }
                            this.lastMsg = "";

                        }

                    }
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            this.pw.close();
            try {
                this.br.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }

        }
    }

}

