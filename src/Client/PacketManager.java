package Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maqsa on 30.06.2017.
 */
public class PacketManager {

    private final static Map<Short, Class<? extends Opacket>> packets = new HashMap<>();

    static {
        packets.put((short) 1, PacketAuthorize.class);
        packets.put((short) 2, PacketMessage.class);
    }

    public static Opacket getPacket(short id){
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
