package sample;

import java.util.ArrayList;

public class Game {
    //Segun la posicion en la lista, sea 0 o 1, se le da el nombre al jugador.
    private ArrayList<Player> players = new ArrayList<Player>();
    private String name;
    private int selectionA;
    private int selectionB;

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

    public void setSelectionA(String selection){

        selectionA = Integer.parseInt(selection);

    }

    public void setSelectionB(String selection){

        selectionB = Integer.parseInt(selection);

    }
    public Boolean isReady() throws InterruptedException {
        if(selectionA!=0 && selectionB!=0){
            match();
            Thread.sleep(1000);
            return true;
        }else{
            return false;
        }
    }
    public void match(){
        int score = selectionA-selectionB;
        /*
         * Este switch controla el resultado del match, si el resultado es -2 o 1 el jugador A
         * gano la partida, si es -1 o 2, el jugador B la gano... Pero si el resultado es 0, significa
         * que fue empate, por lo que la variable "tie" de cada jugador se pone en true.
         * */
        switch (score){
            case -2:
                if(players.get(0).getRoll()){
                    players.get(0).setWisser(true);
                    players.get(1).setWisser(false);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }else{
                    players.get(0).setWisser(false);
                    players.get(1).setWisser(true);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }
                break;
            case -1:
                if(players.get(0).getRoll()){
                    players.get(0).setWisser(false);
                    players.get(1).setWisser(true);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }else{
                    players.get(0).setWisser(true);
                    players.get(1).setWisser(false);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }
                break;
            case 1:
                if(players.get(0).getRoll()){
                    players.get(0).setWisser(true);
                    players.get(1).setWisser(false);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }else{
                    players.get(0).setWisser(false);
                    players.get(1).setWisser(true);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }
                break;
            case 2:
                if(players.get(0).getRoll()){
                    players.get(0).setWisser(false);
                    players.get(1).setWisser(true);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }else{
                    players.get(0).setWisser(true);
                    players.get(1).setWisser(false);
                    players.get(0).setTie(false);
                    players.get(1).setTie(false);
                }
                break;
            case 0:
                players.get(0).setTie(true);
                players.get(1).setTie(true);
                break;
        }
    }
}
