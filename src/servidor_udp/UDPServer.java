package servidor_udp;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

public class UDPServer{
    public static void main(String args[]){

        File tamanhoEmDisco = new File("c:/");
        DatagramSocket aSocket = null;
        ZonedDateTime datadistema = ZonedDateTime.now();
        double conversao = 9.31322575 * Math.pow(10, -10);
        try{
            aSocket = new DatagramSocket(6789);
            byte[] ret = new byte[1000];
            byte[] buffer = new byte[1000];


            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String comando = new String(request.getData());
                String[] recebidos = comando.split(",");
                if(recebidos[0].trim().equalsIgnoreCase("1")){
                    ret = ("Data do Servidor: " +datadistema).getBytes(StandardCharsets.UTF_8);
                }
                else if(recebidos[0].trim().equalsIgnoreCase("2")){
                    int multiplicacaoConvert = (Integer.parseInt(recebidos[1])*Integer.parseInt(recebidos[2]));
                    ret = ("A multiplicação de "+recebidos[1]+" X "+recebidos[2]+" é : " +Integer.toString(multiplicacaoConvert)).getBytes(StandardCharsets.UTF_8);
                }else if(recebidos[0].trim().equalsIgnoreCase("3")){
                    int valorFatorial = Integer.parseInt(recebidos[1]);
                    ret = ("O fatorial é : " +fatorial(valorFatorial)).getBytes(StandardCharsets.UTF_8);
                }else if(recebidos[0].trim().equalsIgnoreCase("4")){
                    int raiz = Integer.parseInt(recebidos[1]);
                    ret = ("A Raiz de "+raiz+" é : " +Math.sqrt(raiz)).getBytes(StandardCharsets.UTF_8);
                }else if(recebidos[0].trim().equalsIgnoreCase("5")){
                    ret = ("O tamanho disponivel do disco é: " +tamanhoEmDisco.getFreeSpace()*conversao).getBytes(StandardCharsets.UTF_8);
                }else if(recebidos[0].trim().equalsIgnoreCase("6")){
                    ret = ("IP do Servidor: " +request.getAddress()).getBytes(StandardCharsets.UTF_8);
                }else if(recebidos[0].trim().equalsIgnoreCase("7")){
                    ret = ("Porta de Origem:" +request.getPort()).getBytes(StandardCharsets.UTF_8);
                }

                DatagramPacket reply = new DatagramPacket(
                        ret, ret.length, request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }

    public static int fatorial(int x) {
        if (x == 0)
            return 1;
        return x * fatorial(x - 1);
    }
}
