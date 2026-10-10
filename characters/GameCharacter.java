package characters;

import characters.enemies.Enemy;
import characters.player.Player;

public class GameCharacter {
    protected String name;
    protected double health;
    protected double strength;
    protected double speed;
    protected double endurance;
    protected boolean isShielded;
    protected boolean isStunned;
    protected double critChance;
    protected double damageMultiplier;
    protected int position;

    public GameCharacter(){
        isShielded=false;
        damageMultiplier=1;
        critChance=0.10;
        isStunned=false;
    }

    protected void assignAttacks(){}

    public void chooseAction(Player player){}

    public void chooseAction(Enemy[] enemies, Player player){}

    public int getPosition(){
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(double health) {
        this.health += health;
    }

    public void increaseStrengthStat(double strength){
        this.strength+=strength;
    }

    public void increaseSpeedStat(double speed){
        this.speed+=speed;
    }

    public void increaseEnduranceStat(double endurance){
        this.endurance+=endurance;
    }

    public void setStrength(double strength) {
        this.strength *= strength;
    }

    public void setSpeed(double speed) {
        this.speed *= speed;
    }

    public void setEndurance(double endurance) {
        this.endurance *= endurance;
    }

    public void setShield(boolean isShielded) {
        this.isShielded = isShielded;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getStrength() {
        return strength;
    }

    public void setStun(boolean isStunned){
        this.isStunned=isStunned;
    }

    public boolean isStunned(){
        return isStunned;
    }

    public double getSpeed() {
        return speed;
    }

    public double getEndurance() {
        return endurance;
    }

    public boolean isShielded() {
        return isShielded;
    }

    public void setDamageMultiplier(double damageMultiplier){
        this.damageMultiplier+=damageMultiplier;
    }

    public double getDamageMultiplier(){
        return damageMultiplier;
    }

    public double getCritChance(){
        return critChance;
    }

    public void boostCritChance(){
        this.critChance *= 1.5;
    }

    public void setStun(){
        if(Math.random()<0.15)
            isStunned=true;
        else
            isStunned=false;
    }

}
