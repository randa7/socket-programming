/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        try {
            String sentence;
            String modifiedSentence;
            
            //Membuat inputsream
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            //Membuat client socket lalu menghubungkan ke server dengan port 6789
            Socket clientSocket = new Socket("192.168.56.101", 6789);
            //Membuat outputstream ke socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            //Membuat inputstream ke socket
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //Menerima inputan dari user
            sentence = inFromUser.readLine();
            //Mengirimkan informasi ke server
            outToServer.writeBytes(sentence + '\n');
            //Membaca hasil yang dikirimkan dari server
            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}