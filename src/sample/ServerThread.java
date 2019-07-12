package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static sample.Server.games;

public class ServerThread implements Runnable{
    //Prueba
    private String nombre;
    //FINPRUEBA
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private Game newGame;
    public ServerThread(Socket player){
        socket = player;
    }

    @Override
    public void run() {
        Player player;
        try {
            String msg;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            name = in.readUTF();
            player = new Player(name);
            System.out.println(player.getName());
            //Ciclo infinito que mantiene la comunicacion con el jugador
            while (true){
                nombre = player.getName();
                System.out.println("El usuario "+player.getName()+" con ip: "+socket.getInetAddress()+" envio la orden:");
                String order = in.readUTF();
                String[] orders = order.split(";");
                System.out.println(order);
                switch (orders[0]){
                    case "players":
                        int room = Integer.parseInt(orders[1])-1;
                        msg = games.get(room).getNPlayers()+"";
                        out.writeUTF(msg);
                        break;
                    case "close":
                        int nOrder = Integer.parseInt(orders[1])-1;
                        games.get(nOrder).removePlayer(player.getName());
                        break;
                    case "join":
                        break;
                    case "delete":
                        games.remove(Integer.parseInt(orders[1]));
                        break;
                    case "list":
                        msg="";
                        for(int i=0; i<games.size();i++){
                            //Crea una linea de texto que contiene toda la informacion de las partidas separadas por "&" (para el split)
                            msg+=games.get(i).getName()+"\t"+games.get(i).getNPlayers()+"/2&";
                            System.out.println(msg);
                        }
                        System.out.println("La lista de partidas en este punto de la ejecucipon tiene: "+games.size()+" partidas guardadas");
                        out.writeUTF(msg);
                        break;
                    case "listP":
                        msg="";
                        for (int i=0; i<games.size();i++){
                            msg+=games.get(i).getNPlayers()+"/2;";
                        }
                        out.writeUTF(msg);
                        break;
                    case "addPlayer":
                        switch (orders[1]){
                            case "1":
                                System.out.println("añadiendo a "+player.getName()+" a la sala");
                                games.get(0).addPlayer(player);
                                break;
                            case "2":
                                System.out.println("añadiendo a "+player.getName()+" a la sala");
                                games.get(1).addPlayer(player);
                                break;
                            case "3":
                                System.out.println("añadiendo a "+player.getName()+" a la sala");
                                games.get(2).addPlayer(player);
                                break;
                        }
                        break;
                    default:
                        System.out.println("nop, no entro :'v");
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("Se desconecto "+name);
        }
    }
    public void setName(String newName){
        name=newName;
    }
}
