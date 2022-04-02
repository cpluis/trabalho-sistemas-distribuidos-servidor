package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {
    public static void main (String args[]) {
        try{
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
}
class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            clientSocket.getLocalAddress();
            in = new DataInputStream( clientSocket.getInputStream());
            out =new DataOutputStream( clientSocket.getOutputStream());
            out.writeUTF(retorna(in.readUTF()));
            this.start();
        } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
    }
    public  String retorna(String comando){
        String volta = null;
        String[] recebido = comando.split(",");
        if (recebido[0].equalsIgnoreCase("3")){
             recebido = comando.split(",");
            String fatorial = String.valueOf(fatorial(Integer.parseInt(recebido[1])));
            volta = "O Fatorial de "+recebido[1]+" é "+fatorial;
            return volta;
        }else
            if(comando.equalsIgnoreCase("1")){
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
            volta = ("A data do Servidor: " +formattedDate);
            return volta;
        } else if(recebido[0].equalsIgnoreCase("2")){
                return "A multiplicação de "+recebido[1]+" X "+recebido[2]+" é: "+
                        String.valueOf(Integer.parseInt(recebido[1]) * Integer.parseInt(recebido[2]));
            }else if (recebido[0].equalsIgnoreCase("6")){
                return "O endereço IP do Cliente é: "
                        +clientSocket.getLocalAddress().toString();
            }else if (recebido[0].equalsIgnoreCase("5")){
                File tamanhoEmDisco = new File("c:/");
                double conversao = 9.31322575 * Math.pow(10, -10);
                return ("O tamanho disponivel do disco é: " +tamanhoEmDisco.getFreeSpace()*conversao);
            }
            else if (recebido[0].equalsIgnoreCase("7")){
                return String.valueOf("A porta é:  "+clientSocket.getPort());
            }else if (recebido[0].equalsIgnoreCase("4")){
                int raiz = Integer.parseInt(recebido[1]);
                return  "A Raiz de "+raiz+" é : " +Math.sqrt(raiz);
            }else{
                return "Valor não identificado!!";
            }
    }
    public static int fatorial(int x) {
        if (x == 0)
            return 1;
        return x * fatorial(x - 1);
    }
    public void run(){
        try {
            in = new DataInputStream( clientSocket.getInputStream());
            out =new DataOutputStream( clientSocket.getOutputStream());
            String valor = in.readUTF();
            out.writeUTF(in.readUTF());
        } catch(EOFException e) {System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("IO:"+e.getMessage());
        } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
    }
}
