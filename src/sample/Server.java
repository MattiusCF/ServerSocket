package sample;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Server {
    //Inicializa el puerto
    private static final int port = 2027;
    //Numero maximo de conexiones
    private static final int NPlayers = 6;
    //Lista de sockets
    private static LinkedList<Socket> players = new LinkedList<Socket>();
    //Lista de partidas
    public static ArrayList<Game> games = new ArrayList<>();


    public static void main(String[] args){
        try {
            Scanner reader = new Scanner(System.in);
            //Define la ip del servidor
            System.out.print("Por favor ingrese la ip del servidor(xxx.xxx.xxx.xxx): ");
            String host = reader.nextLine();
            InetAddress ip = InetAddress.getByName(host);
            //Crea el socket del servidor
            ServerSocket server = new ServerSocket(port, NPlayers, ip);
            //Ciclo infinito para los nuevos jugadores
            for(int i=1; i<4; i++){
                Game game = new Game(i);
                games.add(game);
                System.out.println("Se acaba de crear y almacenar la sala "+i);
            }
            System.out.println("Esperando nuevos jugadores...");
            System.out.println(server.getLocalSocketAddress());
            while(true){
                //Guarda al jugador nuevo en la lista de sockets
                Socket player = server.accept();
                players.add(player);
                //Crea un hilo que estara atendiendo al jugador y lo pone a la escucha
                Runnable run = new ServerThread(player);
                Thread NThread = new Thread(run);
                System.out.println("entro");
                NThread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
