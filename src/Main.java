import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Thread {
    public static void main(String[] args) {
        ArrayList<Agenda>agendas = new ArrayList<>();
        String email,nome,ano;
        Scanner in = new Scanner(System.in);
        try {
            String msg;
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Servidor Iniciado");
            while(true) {
                //Servidor espera algum cliente entrar
                Socket cliente = server.accept();
                System.out.println("Cliente conectado pela porta: " + cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                saida.flush();
                saida.writeObject("Diga o email");
                email = (String) entrada.readObject();
               // saida.writeObject(email);
                saida.flush();
                //Manda msg para o cliente
                saida.writeObject("Diga o nome");
                //Recebe msg do cliente
                nome = (String) entrada.readObject();
                saida.flush();
                saida.writeObject("Diga o ano");
                ano = (String) entrada.readObject();
               agendas.add(Agenda.criarAgenda(email,nome,ano));
                entrada.close();
                saida.close();
                cliente.close();
                for (Agenda a: agendas) {
                    System.out.println(a.getNome());
                }
            }
            }
        catch (Exception e){
            System.out.println("Erro " + e.getMessage());
        }
        }

    }
