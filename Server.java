/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    public static void main(String[] args) {
        try {
            String clientSentence;
            String capitalizedSentence;
            
            //Membuat socket dengan nama welcomeSocket dan port 6789
            ServerSocket welcomeSocket = new ServerSocket(6789);
            while (true) {
                try {
                    //Menunggu kontak dari client
                    Socket connectionSocket = welcomeSocket.accept();
                    //Membuat inputstream ke socket
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    //Membuat outputstream ke socket
                    DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                    //Membaca informasi yang dikirim oleh client dari socket
                    clientSentence = inFromClient.readLine();
                    //Mencetak ke layar
                    System.out.println("FROM CLIENT: " + clientSentence);
                    //Mengubah inputan dari client ke UpperCase
                    capitalizedSentence = clientSentence.toUpperCase() + '\n';
                    //Menulis hasil yang sudah diubah ke socket.
                    outToClient.writeBytes(capitalizedSentence);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}