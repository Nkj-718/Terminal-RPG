package attacks;

import characters.GameCharacter;
import characters.enemies.*;
import characters.player.*;
import java.util.Random;
import fights.*;

public class Attack {
    private String attackName;
    private double attackPower;
    private String attackEffect;
    private String attackType;
    private int attackRange;

    Random random=new Random();

    public Attack(String attackName, double attackPower, String attackEffect) {
        this.attackName=attackName;
        this.attackPower=attackPower;
        this.attackEffect=attackEffect;
    }

    public Attack(String attackName, String attackType, double attackPower, String attackEffect) {
        this(attackName, attackPower, attackEffect);
        this.attackType=attackType;
    }

    public Attack(String attackName, String attackType, double attackPower, String attackEffect, int attackRange) {
        this(attackName, attackPower, attackEffect);
        this.attackType=attackType;
        this.attackRange=attackRange;
    }

    public String getAttackType(){
        return attackType;
    }

    public int getAttackRange(){
        return attackRange;
    }

    boolean hasMissed(){
        return Math.random()<0.10;
    }

    double calculateDamage(GameCharacter self, GameCharacter target){
        // Base Damage
        double damage=attackPower+(self.getStrength()*0.5);

        // Attack Type
        if(attackType.equals("Heavy"))
            damage*=1.15;
        else if(attackType.equals("Ultimate"))
            damage*=1.40;

        // Damage Multiplier
        damage*=self.getDamageMultiplier();

        // Critical Hit
        double critChance=self.getCritChance();

        if(attackEffect.equals("Critical!"))
            critChance=0.25;
        else if(attackEffect.equals("Guaranteed-Crit!"))
            critChance=1.0;

        boolean critical=Math.random()<critChance;

        if(critical){
            damage*=2;
            System.out.println("CRITICAL HIT!");
        }

        // Target Endurance
        double enduranceMultiplier=100.0/(100.0+target.getEndurance());
        damage*=enduranceMultiplier;

        return damage;
    }

    public String getAttackName(){
        return attackName;
    }

    double adjustPosition(double initialPosition, double distance){
        return initialPosition+distance;
    }

    void triggerEffect(GameCharacter self, GameCharacter target){

        if(attackEffect.equals("Power-Up")){
            self.setDamageMultiplier(0.5);
            System.out.println(self.getName() + "'s damage increased!");
        }

        if(attackEffect.equals("Power-Surge")){
            self.setDamageMultiplier(2);
            System.out.println(self.getName() + "'s damage greatly increased!");
        }

        if(attackEffect.equals("Attack-Boost")){
            self.setStrength(1.5);
            System.out.println(self.getName() + "'s strength increased!");
        }

        if(attackEffect.equals("Stagger")){
                target.setStun();

            if(target.isStunned())
                System.out.println(target.getName() + " is staggered!");
        }

        if(attackEffect.equals("Crit-Boost")){
            self.boostCritChance();
            System.out.println(self.getName() + "'s critical chance increased!");
        }

        if(attackEffect.equals("Speed-Boost")){
            self.setSpeed(1.5);
            System.out.println(self.getName() + "'s speed increased!");
        }

        if(attackEffect.equals("Curse")){
            target.setHealth(-20);
            System.out.println(target.getName() + " was cursed for 20 damage!");
        }

        if(attackEffect.equals("Block-Boost")){
            self.setShield(true);
            self.setDamageMultiplier(0.5);
            System.out.println(self.getName() + " is shielded!");
        }

        if(attackEffect.equals("Self-Destruct")){
            double selfDamage=self.getHealth()/2;
            self.setHealth(-selfDamage);
            System.out.println(self.getName() + " lost " + selfDamage + " HP!");
        }
    }

    public void performAttack(GameCharacter self, GameCharacter target){
        System.out.println("============================================================");
        System.out.println(self.getName() + " uses " + attackName + "!");
        
        if(self instanceof Player){
            int distance=Math.abs(self.getPosition()-target.getPosition());
            
            if(distance>attackRange){
                System.out.println("Attack failed! Target is out of range.");
                return;
            }
        }
        if("Spell".equals(attackType)){
            triggerEffect(self, target);
        }
        else{
            if(target.isShielded()){
                System.out.println(target.getName() + " is shielded.");
                target.setShield(false);
            }
            else if(hasMissed()){
                System.out.println(self.getName() + "'s attack missed!");
            }
            else{
                double damage=calculateDamage(self, target);
                target.setHealth(-damage);

                System.out.println(target.getName() + " takes " + String.format("%.1f", damage) + " damage!");

                triggerEffect(self, target);
            }
        }
        System.out.println("============================================================");
    }

    public void performAttack(GameCharacter self, Enemy[] enemies){

        int index;

        do{
            index=random.nextInt(enemies.length);
        }while(enemies[index].getHealth()<=0);

        System.out.println(self.getName() + " uses " + attackName + "!");

        if(attackEffect.equals("Attack-Boost")){
            enemies[index].setDamageMultiplier(1.5);
            System.out.println(enemies[index].getName() + "'s damage increased!");
        }
        else if(attackEffect.equals("Heal")){
            enemies[index].setHealth(30);
            System.out.println(enemies[index].getName() + " was healed for 30 HP!");
        }
    }

}