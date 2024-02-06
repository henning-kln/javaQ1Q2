package netz;

import javax.swing.JFrame;

import gui.GUI;

public class ClientFlood {
    public void send(String pIp, int pPort){
        send(pIp, pPort, "Test");
    }
    public void send(String pIP, int pPort, String pMessage){
        for(int i=0;i<100;i++){
            Connection c = new Connection(pIP, pPort);
            c.send(pMessage);
        }   
    }

    public static void main(String[] args) {
        ClientFlood c = new ClientFlood();
        c.send("127.0.0.1", 420);
        //Connection c1 = new Connection("127.0.0.1", 420);
        //c1.send("test");
        //JFrame jf = new JFrame();
        //jf.setVisible(true);
    }
}
