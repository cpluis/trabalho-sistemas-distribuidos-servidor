package servidor_udp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient{
    public static void main(String args[]){
        DatagramSocket aSocket = null;
        UIManager.getDefaults().put("OptionPane.background",new Color(7, 7, 7));
        UIManager.put ("Panel.background", new Color(220, 152, 6));
        UIManager.put("OptionPane.minimumSize", new Dimension(600, 100));
        try {
            aSocket = new DatagramSocket();
            String aux = null;
            String comando = null;
            Boolean controle = true;
            while (controle == true) {
                comando = JOptionPane.showInputDialog("" +
                        "\n=*=*==*=*=    SELECIONE UMA DAS OPÇÕES   *=*==*=*\n\n\n" +
                        "    1 - Para saber a data e hora do servidor  \n\n" +
                        "    2 - Para realizar de uma Multiplicação    \n\n" +
                        "    3 - Para realizar de um  Fatorial         \n\n" +
                        "    4 - Para saber o valore de uma Raiz         \n\n" +
                        "    5 - Para saber o Tamanho disponível em disco\n\n" +
                        "    6 - Para saber qual o IP do servidor        \n\n" +
                        "    7 - Para saber qual a porta do servidor   \n\n" +
                        "    8 - Para Encerar.     \n\n\n\n\n\n"
                );
                if(comando == null || comando.trim().equalsIgnoreCase("")){
                    continue;
                }
                int comandoConvertido = Integer.parseInt(comando);
                if(comandoConvertido < 1 || comandoConvertido > 8){
                    JOptionPane.showMessageDialog(null,"Valor inválido deve buscar de 1 a 8 ");
                    continue;
                }
                if (comandoConvertido == 8) {
                    JOptionPane.showMessageDialog(null, "Valeu! até a próxima!");
                    break;
                }
            if (comando.trim().equalsIgnoreCase("8")) {
                System.exit(0);
            }
            if (comando.trim().equalsIgnoreCase("4")) {
                aux = JOptionPane.showInputDialog("Digite um valor da raiz");

                comando = comando + "," + aux + ",";
            }
            if (comando.trim().equalsIgnoreCase("3")) {
                aux = JOptionPane.showInputDialog("Digite um valor do fatorial");
                comando = comando + "," + aux + ",";
            } else if (comando.trim().equalsIgnoreCase("2")) {
                String valor1 = JOptionPane.showInputDialog("Digite o prieiro valor da multiplicação");
                String valor2 = JOptionPane.showInputDialog("Digite o segundo valor da multiplicação");
                aux = valor1 + "," + valor2;
                comando = comando + "," + aux + ",";
            }
            byte[] m = comando.getBytes();
            double v = .0;
            String endereco = "127.0.0.1";
            InetAddress aHost = InetAddress.getByName(endereco);
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
            aSocket.send(request);
            byte[] buffer = new byte[50];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
                JOptionPane.showMessageDialog(null, new String(reply.getData()));
        }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}
