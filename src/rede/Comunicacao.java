package rede;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Comunicacao {
     public static void respostaServidor(String comando) throws IOException {
        DatagramSocket aSocket = new DatagramSocket();
        byte[] m = comando.getBytes();
        double v = .0;
        String hostServer = "127.0.0.1";
        InetAddress aHost = InetAddress.getByName(hostServer);
        int serverPort = 6789;
        DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
        aSocket.send(request);
        byte[] buffer = new byte[50];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        aSocket.receive(reply);
        JOptionPane.showMessageDialog(null, new String(reply.getData()));
    }
}
