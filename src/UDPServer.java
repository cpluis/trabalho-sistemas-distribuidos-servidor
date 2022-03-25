import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

public class UDPServer{
    public static void main(String args[]){
        DatagramSocket aSocket = null;
        ZonedDateTime datadistema = ZonedDateTime.now();
        double conversao = 9.31322575 * Math.pow(10, -10);
        try{
            aSocket = new DatagramSocket(6789);

            byte[] ret = new byte[1000];
            byte[] buffer = new byte[1000];
            File tamanhoEmDisco = new File("c:/");
            int valorFatorial = 9;
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String comando = new String(request.getData());
                if(comando.trim().equalsIgnoreCase("porta")){
                    ret = ("Porta de Origem:" +request.getPort()).getBytes(StandardCharsets.UTF_8);
                }else if(comando.trim().equalsIgnoreCase("data")){
                    ret = ("Data do Servidor: " +datadistema).getBytes(StandardCharsets.UTF_8);
                }else if(comando.trim().equalsIgnoreCase("ip")){
                    ret = ("IP do Servidor: " +request.getAddress()).getBytes(StandardCharsets.UTF_8);
                }else if(comando.trim().equalsIgnoreCase("tamanho disco")){
                    ret = ("O tamanho disponivel do disco é: " +tamanhoEmDisco.getFreeSpace()*conversao).getBytes(StandardCharsets.UTF_8);
                }else if(comando.trim().equalsIgnoreCase("fatorial")){
                    ret = ("O fatorial é : " +fatorial((valorFatorial))).getBytes(StandardCharsets.UTF_8);
                }else if(comando.trim().equalsIgnoreCase("multiplicacao")){
                    ret = ("A multiplicação de 5 X 6 é : " +(5*6)).getBytes(StandardCharsets.UTF_8);
                }else if(comando.trim().equalsIgnoreCase("raiz")){
                    ret = ("A Raiz de 6 é : " +Math.sqrt(6)).getBytes(StandardCharsets.UTF_8);
                }

                DatagramPacket reply = new DatagramPacket(
                        ret, ret.length, request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }

    public static int fatorial(int valorRecebido) {
        int fatorial = valorRecebido;
        for (int n = 1; n <= 10; n++) {
            fatorial = n;
            for (int fat = (n - 1); fat >= 1; fat--) {
                if (fat == 0) {
                    fat = 1;
                }
                fatorial = fatorial * fat;
            }
        }
        return fatorial;
    }
}
