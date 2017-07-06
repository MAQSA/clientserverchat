package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by maqsa on 30.06.2017.
 */
public abstract class Opacket {

    public abstract short getId();

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

    public abstract void handle();
}
