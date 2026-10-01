package characters.enemies;

import attacks.Attack;
import characters.player.Player;

public class Archer extends Enemy{
    private Attack[] attacks=new Attack[1];

    public Archer(){
        super();
        name="Archer";
        health=100;
        strength=20;
        speed=50;
        endurance=25;
        reward=10;
        position=10;
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Arrow Barrage","Heavy", 25, "None");
    }

    @Override 
    public void chooseAction(Player player){
        //1. Attack     2. Move Closer      3. Move Away
        int distance=calculateDistance(player.getPosition());
        if(distance<4){
            if(Math.random()<0.30){
                attacks[0].performAttack(this, player);
            }
            else{
                position+=5;
                System.out.println(getName() + " moved away.");
            }
        }
        else if(distance>=4 && distance<15){
            if(Math.random()<0.80){
                attacks[0].performAttack(this, player);
            }
            else{
                position+=5;
                System.out.println(getName() + " moved away.");
            }
        }
        else{
            position-=5;
            System.out.println(getName() + " moved closer.");
        }
    }
}