package Interface;

import javax.swing.*;
import java.awt.*;

public class JoptionPane {
    public String faceJotionPaneUDP(){
        UIManager.getDefaults().put("OptionPane.background",new Color(7, 7, 7));
        UIManager.put ("Panel.background", new Color(220, 152, 6));
        UIManager.put("OptionPane.minimumSize", new Dimension(600, 100));
    return joptionPane("UDP");
    }

    public String faceJotionPaneTCP(){
        UIManager.getDefaults().put("OptionPane.background",new Color(7, 7, 7));
        UIManager.put ("Panel.background", new Color(3, 232, 198));
        UIManager.put("OptionPane.minimumSize", new Dimension(600, 100));
        return joptionPane("TCP");
    }

    public String telaPequena(){
        UIManager.getDefaults().put("OptionPane.background",new Color(7, 7, 7));
        UIManager.put ("Panel.background", new Color(185, 127, 3));
        UIManager.put("OptionPane.minimumSize", new Dimension(200, 100));
        return joptionPane("TCP");
    }

    public String telaIntupPequena(String frase){
        UIManager.getDefaults().put("OptionPane.background",new Color(7, 7, 7));
        UIManager.put ("Panel.background", new Color(185, 127, 3));
        UIManager.put("OptionPane.minimumSize", new Dimension(360, 100));
        return  JOptionPane.showInputDialog(frase);
    }

    private String joptionPane(String server) {
        return  JOptionPane.showInputDialog("" +
                "\nServidor =======> "+server+
                "\n=*=   SELECIONE UMA DAS OPÇÕES   =*=\n\n" +
                "    1 - Para saber a data e hora do servidor  \n" +
                "    2 - Para realizar de uma Multiplicação    \n" +
                "    3 - Para realizar de um  Fatorial         \n" +
                "    4 - Para saber o valore de uma Raiz         \n" +
                "    5 - Para saber o Tamanho disponível em disco\n" +
                "    6 - Para saber qual o IP de Origem      \n" +
                "    7 - Para saber qual a porta do servidor   \n" +
                "    8 - Para Encerar.     \n\n\n\n"
        );
    }
}
