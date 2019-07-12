package sample;

import java.util.ArrayList;

public class Game {
    //Segun la posicion en la lista, sea 0 o 1, se le da el nombre al jugador.
    private ArrayList<Player> players = new ArrayList<Player>();
    private String name;
    private int selectionA;
    private int selectionB;
    private int resultMatch;
    private int matches;

    public Game(int NGame){
        name = "Sala "+NGame;
    }

    public int getNPlayers() {
        return players.size();
    }

    public void addPlayer(Player player){
        if (players.size()!=0){
            //Si hay otro jugador en la sala, le pone al nuevo el roll contrario al jugador existente
            player.setRoll(!players.get(0).getRoll());
            player.setGame(this);
        }else{
            //Si es el primer jugador en entrar a la sala, se le asigna el roll A (true) por defecto
            player.setRoll(true);
        }
        players.add(player);
    }
    public void removePlayer(String name){
        for (int i=0;i<players.size();i++){
            if(players.get(i).getName() == name){
                players.remove(i);
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getResultMatch() {
        return resultMatch;
    }
}
