package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by maqsa on 30.06.2017.
 */
public class PacketAuthorize extends Opacket {
    private String nickname;

    public PacketAuthorize() {
    }

    public PacketAuthorize(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(nickname);

    }

    @Override
    public void read(DataInputStream dis) throws IOException {

    }

    @Override
    public void handle() {

    }
}
