package characters.enemies;

import attacks.Attack;
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

    protected void assignAttacks(){}

    public void chooseAction(){}

}