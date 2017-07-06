package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by maqsa on 30.06.2017.
 */
public abstract class Opacket extends Client.Opacket {
    private Socket socket;

    public Opacket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract short getId();

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

    public abstract void handle();

}
