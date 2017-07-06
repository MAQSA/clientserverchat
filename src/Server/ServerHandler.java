package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by maqsa on 30.06.2017.
 */
public class ServerHandler extends Thread{

    private final ServerSocket server;

    ServerHandler(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (true){
            try {
                Socket client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                handler.start();
                ServerLoader.handlers.put(client, handler);
            } catch (SocketException e){
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //подключаем клиентов
            try {
                Thread.sleep(10);
            } catch (InterruptedException e){}
        }    }
}
