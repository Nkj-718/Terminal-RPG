package characters.player;

import attacks.Attack;

public class Barbarian extends Player{
    public Barbarian(){
        health=100;
        strength=60;
        speed=35;
        endurance=75;
        weapon="Club";
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Savage Swing", "Light", 25, "Stagger", 5);
        attacks[1]=new Attack("Earthshatter", "Heavy", 40, "Stagger", 4);
        attacks[2]=new Attack("Blind Rush", "Spell", 20, "Attack-Boost", 12);
        attacks[3]=new Attack("Ultimate: Worldbreaker", "Ultimate", 180, "None", 6);
    }
}
