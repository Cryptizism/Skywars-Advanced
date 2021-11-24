package net.cryptizism.skywars.manager;

public class GameManager {

    public GameState gameState = GameState.LOBBY;


    public void endGame(){
        gameState = GameState.WON;
    }

    public void mapChange(){

    }


}
