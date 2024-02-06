package netz;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;

class User{
    private String nickname;
    private String IP;
    private int port;
    private boolean blocked;
    private boolean firstMessage = true;

    
    public boolean isFirstMessage() {
        return firstMessage;
    }
    public void setFirstMessage(boolean firstMessage) {
        this.firstMessage = firstMessage;
    }
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public User(String nickname, String iP, int port) {
        this.nickname = nickname;
        IP = iP;
        this.port = port;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getIP() {
        return IP;
    }
    public void setIP(String iP) {
        IP = iP;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String toString(){
        return nickname+":"+IP+":"+port+":"+blocked;
    }

    
}


public class ChatServer extends Server {
    private List<User> users;

    public ChatServer() {
        super(420);
        users = new ListWithViewer<User>();
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        User u = new User("Kein name", pClientIP, pClientPort);
        users.append(u);
        this.send(pClientIP, pClientPort, "Für hilfe schicke +help");
        this.sendToAll("Client:"+pClientIP+":"+pClientPort);
    }
    public User getUser(String nickname){
        synchronized(users){
            for(users.toFirst();users.hasAccess();users.next()){
                User u = users.getContent();
                if(u.getNickname().equals(nickname)){
                    return u;
                }
            }            
        }
        return null;
    }

    public User getUser(String pClientIP, int pClientPort){
        synchronized(users){
            for(users.toFirst();users.hasAccess();users.next()){
                User u = users.getContent();
                if(u.getIP().equals(pClientIP) && u.getPort()==pClientPort){
                    return u;
                }
            }
        }
        
        return null;
    }

    public void removeUser(String pClientIP, int pClientPort){
        synchronized(users){
            for(users.toFirst();users.hasAccess();users.next()){
                User u = users.getContent();
                if(u.getIP().equals(pClientIP) && u.getPort()==pClientPort){
                    users.remove();
                    return;
                }
            }
        }
        
        return;
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        if(pMessage.startsWith("+name ")){
            pMessage = pMessage.replace("+name ", "");
            if(pMessage.length()<3){
                this.send(pClientIP, pClientPort, "-Err zu kurz");
                return;
            }
            User u = getUser(pClientIP, pClientPort);
            if(u==null){
                this.send(pClientIP, pClientPort, "-Err bereit vergeben");
                return;
            }
            u.setFirstMessage(false);
            u.setNickname(pMessage);
            this.send(pClientIP, pClientPort, "+Nickname set");
            return;
        }
        else if(pMessage.startsWith("+block123 ")){
            pMessage = pMessage.replace("+block123 ", "");
            User u = getUser(pMessage);
            User u2 = getUser(pClientIP, pClientPort);
            u2.setFirstMessage(false);
            if(u==null){
                this.send(pClientIP, pClientPort, "-Err Benutzer gibt es nicht");
                return;
            }
            u.setBlocked(true);
            return;

        }
        else if(pMessage.startsWith("+dos ")){
            pMessage = pMessage.replace("+dos ", "");
            User u = getUser(pMessage);
            User u2 = getUser(pClientIP, pClientPort);
            u2.setFirstMessage(false);
            if(u!=null){
                for(int i=0;i<100;i++){
                    this.send(u.getIP(), u.getPort(), "Dos");
                }
                this.send(pClientIP, pClientPort, "+OK dos send");
                return;
            }
            this.send(pClientIP, pClientPort, "-Err kein benutzer gefunden");
        }else if(pMessage.startsWith("+help")){
            User u = getUser(pClientIP, pClientPort);
            u.setFirstMessage(false);
            this.send(pClientIP, pClientPort, "+name <name>");
            this.send(pClientIP, pClientPort, "+block <nickname>");
            this.send(pClientIP, pClientPort, "+m <nachricht>");
            this.send(pClientIP, pClientPort, "+quit verlassen");
            return;
        }else if(pMessage.startsWith("+m ")){
            pMessage = pMessage.replace("+m ", "");
            User u = getUser(pClientIP, pClientPort);
            u.setFirstMessage(false);
            if(u.getNickname().equals("Kein name")){
                this.send(pClientIP, pClientPort, "-Err du musst dir erst einen nickname geben");
                return;
            }
            if(u.isBlocked()){
                this.send(pClientIP, pClientPort, "-Err du bist blockiert");
                return;
            }
            this.sendToAll(u.getNickname()+": "+pMessage);
        }else if(pMessage.startsWith("+quit")){
            this.closeConnection(pClientIP, pClientPort);
            return;
        }else{
            User u = getUser(pClientIP, pClientPort);
            if(u.isFirstMessage()){
                this.send(pClientIP, pClientPort, "-Err Fehler. Verbindung wird geschlossen!");
                this.closeConnection(pClientIP, pClientPort);
                return;
            }
            this.send(pClientIP, pClientPort, "-Err konnte nicht zugestellt werden");
            return;
           
        }
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        removeUser(pClientIP, pClientPort);
        this.sendToAll(pClientIP+":"+pClientPort+" verabschiedet sich");
    }
    
    public static void main(String[] args) {
        new ChatServer();
    }

}
