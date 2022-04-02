package servidores;

import Interface.JoptionPane;
import excecao.ExcecaoClient;
import rede.Comunicacao;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        JoptionPane pane = new JoptionPane();
        try {
            aSocket = new DatagramSocket();
            String aux = null;
            String comando = null;
            Boolean controle = true; 
            while (controle == true) {
                comando = pane.faceJotionPaneUDP();
                if (comando == null || comando.trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Para encerrar digite o valor 8");
                    continue;
                }
                if (comando.length() > 1) {
                    JOptionPane.showMessageDialog(null,"Valor inválido deve buscar de 1 a 8 " );
                    continue;
                }
                int comandoConvertido = Integer.parseInt(comando);
                if (comandoConvertido < 1 || comandoConvertido > 8 || comando.trim().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Valor inválido, digite um valor entre 1 a 8 ");
                    continue;
                } else if (comandoConvertido == 8) {
                    JOptionPane.showMessageDialog(null, "Valeu! até a próxima!");
                    break;
                } else if (comando.trim().equalsIgnoreCase("8")) {
                    System.exit(0);
                } else if (comando.trim().equalsIgnoreCase("4")) {
                    aux = JOptionPane.showInputDialog("Digite um valor da raiz");
                    comando = comando + "," + aux + ",";
                } else if (comando.trim().equalsIgnoreCase("3")) {
                    aux = pane.telaIntupPequena("Digite um valor do fatorial");
                    comando = comando + "," + aux + ",";
                } else if (comando.trim().equalsIgnoreCase("2")) {
                    String valor1 = pane.telaIntupPequena("Digite o primeiro valor da multiplicação");
                    String valor2 = pane.telaIntupPequena("Digite o segundo valor da multiplicação");
                    aux = valor1 + "," + valor2;
                    comando = comando + "," + aux + ",";
                }
                Comunicacao.respostaServidor(comando);
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (aSocket != null) aSocket.close();
        }
    }
}


