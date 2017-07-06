package Client;

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
        dos.writeUTF(message);
    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        sender = dis.readUTF();
        message = dis.readUTF();
    }

    @Override
    public void handle() {
        System.out.format("[%s]", sender, message);
    }
}
