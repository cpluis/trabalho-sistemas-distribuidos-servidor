package tcp;

import Interface.JoptionPane;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
public class TCPClient {
    public static void main (String args[]) {
        Socket s = null;
        JoptionPane pane = new JoptionPane();
        EnvioERespostaDoServer doServer = new EnvioERespostaDoServer();
        try{
            String aux = null;
            String comando = null;
            Boolean controle = true;
            while (controle == true) {
                comando = comando = pane.faceJotionPaneTCP();
                if (comando == null || comando.trim().equalsIgnoreCase("")) {
                    continue;
                }
                int comandoConvertido = Integer.parseInt(comando);
                if (comandoConvertido < 1 || comandoConvertido > 8) {
                    JOptionPane.showMessageDialog(null, "Valor inválido deve buscar de 1 a 8 ");
                    continue;
                }
                else if (comandoConvertido == 8) {
                    UIManager.put("OptionPane.minimumSize", new Dimension(300, 100));
                    JOptionPane.showMessageDialog(null, "Valeu! até a próxima!");
                    break;
                }
                else if(comando.trim().equalsIgnoreCase("8")) {
                    System.exit(0);
                }
                else if (comando.trim().equalsIgnoreCase("4")) {
                    aux = JOptionPane.showInputDialog("Digite um valor da raiz");
                    comando = comando + "," + aux + ",";
                }
                if (comando.trim().equalsIgnoreCase("3")) {
                        UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
                        aux = JOptionPane.showInputDialog("Digite um valor do fatorial");
                        if (aux == null) {
                            while (true) {
                            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
                            JOptionPane.showMessageDialog(null,"Valor invalido");
                            aux = JOptionPane.showInputDialog("Digite um valor do fatorial");
                                if (aux != null) {
                                    break;
                                }
                                continue;
                            }
                        }
                    comando = comando + "," + aux + ",";
                }if (comando.trim().equalsIgnoreCase("2")) {
                    comando = condicional(comando,aux);
                }
                doServer.enviaERecebedoServer(comando);
            }
        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Valor não permitido digite apenas números");
        }
        catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("IO:"+e.getMessage());
        }finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }

    private static String condicional(String comando,String aux) {
            String valor1 = JOptionPane.showInputDialog("Digite o prieiro valor da multiplicação");
            String valor2 = JOptionPane.showInputDialog("Digite o segundo valor da multiplicação");
            aux = valor1 + "," + valor2;
           return comando = comando + "," + aux + ",";
    }
}
