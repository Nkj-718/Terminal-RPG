package characters.player;

import attacks.Attack;

public class Duelist extends Player{
    public Duelist(){
        health=70;
        strength=45;
        speed=60;
        endurance=60;
        weapon="Spear";
        critChance=0.25;
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Quick Thrust", "Light", 30, "Critical!", 8);
        attacks[1]=new Attack("Impaling Lunge", "Heavy", 50, "Critical!", 7);
        attacks[2]=new Attack("Hawk's Gaze", "Spell", 0, "Crit-Boost", 12);
        attacks[3]=new Attack("Ultimate: Dance of Spears", "Ultimate", 100, "Guaranteed-Crit!", 9);
    }
}
