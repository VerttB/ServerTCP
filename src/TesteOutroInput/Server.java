package TesteOutroInput;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port){
        this.port = port;
    }


    public void start(){
        try {
            ServerSocket server = new ServerSocket(this.port);
            Socket cliente = server.accept();
            InputStreamReader inputStreamReader = new InputStreamReader(cliente.getInputStream()) ;
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String msg = reader.readLine();
            System.out.println("Cliente: " + msg);
            PrintStream send = new PrintStream(cliente.getOutputStream());
            send.println("Bom Dia cliente");

        }
        catch (Exception e){
            e.getMessage();
        }

    }

    public static void main(String[] args) {
        Server server = new Server(12345);
        server.start();
    }
}
