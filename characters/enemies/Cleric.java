package characters.enemies;

import attacks.Attack;
import characters.player.*;
import java.util.Random;

public class Cleric extends Enemy{
    private Attack[] attacks=new Attack[2];
    Random random=new Random();

    public Cleric(){
        super();
        name="Cleric";
        health=80;
        strength=0;
        speed=45;
        endurance=25;
        reward=10;
        position=8;
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Healing Spell", "Spell", 0, "Heal");
        attacks[1]=new Attack("Damage Boost", "Spell", 0, "Attack-Boost");
    }

    @Override 
    public void chooseAction(Enemy[] enemies, Player player){
        double distance=calculateDistance(player.getPosition());

        //Perform either attacks[0](Healing Spell-25%), attacks[1](Damage Boost-25%), or Move away(50%)
        if(distance<6){
            if(Math.random()<0.50){
                move(player, false, 5);
            }
            else{
                attacks[random.nextInt(2)].performAttack(this, enemies);
            }
        }

        //Either Perform attacks[0](Healing Spell-30%), attacks[1](Damage Boost-40%), or Move Away(20%)
        else{
            double value=Math.random();

            if(value<0.40){
                attacks[1].performAttack(this, enemies);
            }
            else if(value>=0.40 && value<0.70){
                attacks[0].performAttack(this, enemies);
            }
            else{
                move(player, false, 5);
            }
        }
    }

}