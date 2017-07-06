package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by maqsa on 30.06.2017.
 */
public class ClientHandler extends Thread{
    private final Socket client;
    private String nickname = "Неизвестный";

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClientHandler(Socket client){
        this.client = client;
        start();
    }

    public void invalidate(){
        ServerLoader.invalidate(client);
    }

    private boolean readData(){
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() <= 0){
                return false;
            }
            short id = dis.readShort();
            //читаем пакет
            Opacket opacket = PacketManager.getPacket(id);
            opacket.setSocket(client);
            opacket.read(dis);
            opacket.handle();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void run() {
        while (true) {
            if (!readData()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
        }
    }
}
