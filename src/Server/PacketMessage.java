package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Created by maqsa on 30.06.2017.
 */
public class PacketMessage extends Opacket {
    private String sender, message;

    public PacketMessage() {
    }

    public PacketMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(sender);
        dos.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        message = dis.readUTF();
    }

    @Override
    public void handle() {
        sender = ServerLoader.getHandler(getSocket()).getNickname();
        ServerLoader.handlers.keySet().forEach(s -> ServerLoader.sendPacket(s, this));
        System.out.format("[%s] %s", sender, message);

    }
}
