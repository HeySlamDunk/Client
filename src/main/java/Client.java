import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread {

    MorraInfo x;
    int port;
    String ip;
    Socket socketClient;
    ObjectOutputStream out;
    ObjectInputStream in;

    private Consumer<Serializable> callback;
    private Consumer<Serializable> game;


    Client(Consumer<Serializable>call, int port, String ip, Consumer<Serializable> game){
        this.port = port;
        this.ip = ip;
        callback = call;
        this.game = game;

    }


    public void run() {

        try {
            // IP: "127.0.0.1"
            socketClient = new Socket(ip, port);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
            String message = in.readObject().toString();
            callback.accept(message);
        } catch (Exception e) {}
        while(true) {
            try {
                x = (MorraInfo) in.readObject();
                game.accept(x);

            } catch (Exception e) {

            }
        }
    }


    public void send(Serializable data) {
        try {
            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}