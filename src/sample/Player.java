package sample;

public class Player {
    private String name;
    private Game game;
    //Winner or losser variable
    private Boolean wisser;
    //true A, false B
    private boolean roll;
    //Debe iniciar en 0 y una partida acaba si uno de los dos jugadores obtiene la mitad mas uno de la cantidad de rondas
    private int points;
    private Boolean tie=false;

    public Player(String name){
        this.name=name;
        roll=false;
        points=0;
    }
    public void setRoll(boolean asignedRoll){
        roll=asignedRoll;
    }
    public boolean getRoll(){
        return roll;
    }
    public String getName(){
        return name;
    }
    public void setGame(Game game){
        this.game=game;
    }
    public void setWisser(Boolean w){
        wisser = w;
    }
    public  Boolean isWinner(){
        return wisser;
    }
    public void setTie(Boolean t){
        tie = t;
    }
    public Boolean isTie(){
        return tie;
    }
}
