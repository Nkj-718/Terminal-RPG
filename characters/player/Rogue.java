package characters.player;

import attacks.Attack;

public class Rogue extends Player{
    public Rogue(){
        health=60;
        strength=45;
        speed=80;
        endurance=50;
        weapon="Gauntlets";
        assignAttacks();
    }

    @Override 
    protected void assignAttacks(){
        attacks[0]=new Attack("Rapid Strike", "Light", 25, "Power-Up", 6);
        attacks[1]=new Attack("Shadowbreaker", "Heavy", 40, "Power-Up", 5);
        attacks[2]=new Attack("Flaring Spirit", "Spell", 0, "Power-Surge", 12);
        attacks[3]=new Attack("Ultimate: Thousand Fists", "Ultimate", 100, "Power-Surge", 5);
    }
}
