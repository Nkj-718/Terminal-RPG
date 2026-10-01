package characters.player;

import attacks.Attack;

public class Swordsman extends Player{
    public Swordsman(){
        health=70;
        strength=40;
        speed=65;
        endurance=60;
        weapon="Sword";
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Quick Slash", "Light", 20, "Curse", 6);
        attacks[1]=new Attack("Cleaving Strike", "Heavy", 30, "Curse", 5);
        attacks[2]=new Attack("Flash-Feet", "Spell", 0, "Speed-Boost", 12);
        attacks[3]=new Attack("Ultimate: King's Execution", "Ultimate", 100, "Speed-Boost", 7);
    }
}
