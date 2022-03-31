package tcp;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EnvioERespostaDoServer {

    public void enviaERecebedoServer(String comando) throws IOException {
        Socket s = null;
        String hostServer = "127.0.0.1";
        String frase = "teste ";//tirar depois
        int serverPort = 7896;
        s = new Socket(hostServer, serverPort);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        out.writeUTF(comando);

        String data = in.readUTF();
        JOptionPane.showMessageDialog(null,data);
    }
}
