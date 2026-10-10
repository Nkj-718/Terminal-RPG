package characters.enemies;

import characters.player.*;
import attacks.Attack;

public class Warden extends Enemy{
    private Attack[] attacks=new Attack[2];

    public Warden(){
        super();
        name="Warden";
        health=200;
        strength=30;
        speed=35;
        endurance=50;
        reward=20;
        position=5;
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Axe Hammer", "Heavy", 35, "Stagger");
        attacks[1]=new Attack("Boulder Throw", "Heavy", 30, "None");
    }

    @Override 
    public void chooseAction(Player player){
        double distance=calculateDistance(player.getPosition());
        //Perform either attacks[0](Axe Hammer-70%), move away(15%), or move closer(15%).
        if(distance<5){
            double value=Math.random();
            if(value<0.70)
                attacks[0].performAttack(this, player);
            else if(value>=0.70 && value<0.85){
                move(player, false, 3);
            }
            else{
                move(player, true, 3);
            }
        }
        //Perform either attacks[1](Boulder Throw-70%),move away(5%), or move closer(25%)
        else if(distance>=5 && distance<10){
            double value=Math.random();
            if(value<0.70)
                attacks[1].performAttack(this, player);
            else if(value>=0.70 && value<0.75){
                move(player, false, 3);
            }
            else{
                move(player, true, 3);
            }
        }
        else{
            move(player, true, 3);
        }
    }
}