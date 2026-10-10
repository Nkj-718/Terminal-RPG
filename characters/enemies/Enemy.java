package characters.enemies;

import characters.GameCharacter;
import characters.player.Player;

public class Enemy extends GameCharacter{
    
    protected int reward;
    protected boolean isCursed;
    
    public Enemy(){
        damageMultiplier=1;
        isCursed=false;
        isStunned=false;
    }

    public int giveReward(){
        return reward;
    }

    int calculateDistance(int playerPosition){
        return Math.abs(position-playerPosition);
    }

    void move(Player player, boolean close, int distance){
        if(close){
            if(player.getPosition()<=getPosition()){
                position-=distance;
            }
            else{
                position+=distance;
            }
            System.out.println(getName() + " moved " + distance + "m towards " + player.getName());
        }
        else if(!close){
            if(player.getPosition()<=getPosition()){
                position+=distance;
            }
            else{
                position-=distance;
            }
            System.out.println(getName() + " moved " + distance + "m away from " + player.getName());
        }
    }

    protected void assignAttacks(){}

    public void chooseAction(){}

}