package Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by maqsa on 30.06.2017.
 */
public class ClientLoader {
    private static Socket socket;
    private static boolean sendNickname = false;

    public static void main(String[] args) {
        connect();
        handle();
        end();
    }

    public static void sendPacket(Opacket opacket){
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeShort(opacket.getId());
            opacket.write(dos);
            dos.flush();
        } catch (IOException e){

        }
    }

    private static void connect(){
        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle(){
        Thread handler = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        if (dis.available() <= 0)
                        {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {}
                            continue;

                        }
                        short id = dis.readShort();
                        Opacket packet = PacketManager.getPacket(id);
                        packet.read(dis);
                        packet.handle();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        handler.start();
        readChat();
    }

    private static void readChat(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            if (scanner.hasNextLine()){
                String line = scanner.nextLine();
                if (line.equals("/end")) end();
                if (!sendNickname){
                    sendNickname = true;
                    sendPacket(new PacketAuthorize(line));
                    continue;
                }
                sendPacket(new PacketMessage(null, line));

            }else{
                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                }
            }
        }

    }
    private static void end(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
