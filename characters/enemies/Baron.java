package characters.enemies;

import attacks.Attack;
import characters.player.*;

public class Baron extends Enemy{
    private Attack[] attacks=new Attack[4];

    public Baron(){
        super();
        name="The Baron";
        health=350;
        strength=50;
        speed=50;
        endurance=75;
        reward=40;
        position=7;
        assignAttacks();
    }

    public Baron(String phase2){
        this();
        name="Baron(Unleashed)";
        health=550;
        strength=70;
        speed=60;
        endurance=85;
        reward=75;
        position=8;
        assignAttacks2();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Full Counter", "Spell",0, "Block-Boost");
        attacks[1]=new Attack("Axe Rampage", "Heavy",50, "None");
        attacks[2]=new Attack("Shockwave", "Heavy",40, "None");
        attacks[3]=new Attack("Detonation", "Heavy",100, "Self-Destruct");
    }

    private void assignAttacks2(){
        attacks[0]=new Attack("Full Counter", "Spell",0, "Block-Boost");
        attacks[1]=new Attack("Axe Rampage", "Heavy",65, "None");
        attacks[2]=new Attack("Shockwave", "Heavy",50, "None");
        attacks[3]=new Attack("Detonation", "Heavy",120, "Self-Destruct");
    }

    @Override 
    public void chooseAction(Player player){
        double distance=super.calculateDistance(player.getPosition());

        if(distance<6){
            //Perform either attacks[0](Full Counter-20%), attacks[1](Axe Rampage-70%), attacks[3](Detonation-5%), or move 5m away(5%). 
            double value=Math.random();
            if(value<0.20)
                attacks[0].performAttack(this, player);
            else if(value>=0.20 && value<0.90)
                attacks[1].performAttack(this, player);
            else if(value>=0.90 && value<0.95)
                attacks[3].performAttack(this, player);
            else{
                move(player, false, 4);
            }
        }
        else if(distance>=6 && distance<15){
            //Perform either attacks[0](Full Counter-20%), attacks[2](Shockwave-50%), or move closer(30%)
            double value=Math.random();
            
            if(value<0.20)
                attacks[0].performAttack(this, player);
            else if(value>=0.20 && value<0.70)
                attacks[2].performAttack(this, player);
            else{
                move(player, true, 4);
            }
        }
        else{
            //Perform either attacks[0](Full Counter-30%), or move closer(70%)
            if(Math.random()<0.30)
                attacks[0].performAttack(this, player);
            else{
                move(player, true, 4);
            }
        }
    }

}