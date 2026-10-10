package characters.enemies;

import attacks.Attack;
import characters.player.*;

public class Knight extends Enemy{
    private Attack[] attacks=new Attack[1];

    public Knight(){
        super();
        name="Knight";
        health=150;
        strength=25;
        speed=40;
        endurance=35;
        reward=10;
        position=4;
        assignAttacks();
    }

    @Override
    protected void assignAttacks(){
        attacks[0]=new Attack("Sword Sweep","Heavy", 25, "None");
    }

    @Override 
    public void chooseAction(Player player){
        //1. Attack     2. Move Closer
        int distance=calculateDistance(player.getPosition());
        if(distance<4){
            attacks[0].performAttack(this, player);
        }
        else{
            move(player, true, 3);
        }
    }
}