package characters.player;

import java.util.Scanner;
import attacks.Attack;
import characters.GameCharacter;
import characters.enemies.*;
import fights.*;

public class Player extends GameCharacter{
    protected String weapon;
    protected Attack[] attacks=new Attack[4];
    private double savedHealth;
    private double savedStrength;
    private double savedSpeed;
    private double savedEndurance;
    private double savedCritChance;
    private int ultimateCharges;

    Scanner sc=new Scanner(System.in);

    public Player(){
        position=0;
        isShielded=false;
        ultimateCharges=0;
    }

    boolean isUltimateCharged(){
        return ultimateCharges>=4;
    }

    public void increaseUltimateCharge(){
        ultimateCharges++;
    }

    public void resetUltimateCharges(){
        ultimateCharges=0;
    }

    public int getUltimateCharges(){
        return ultimateCharges;
    }

    public void setShield(boolean isShielded){
        this.isShielded=isShielded;
    }

    public boolean getShieldStatus(){
        return isShielded;
    }

    public void savePlayer(){
        savedHealth=health;
        savedStrength=strength;
        savedSpeed=speed;
        savedEndurance=endurance;
        savedCritChance=critChance;
    }

    public void resetPlayer(){
        health=savedHealth;
        strength=savedStrength;
        speed=savedSpeed;
        critChance=savedCritChance;
        endurance=savedEndurance;
        damageMultiplier=1;
        position=0;
        isShielded=false;
        isStunned=false;
    }

    public void setPosition(Enemy target, String direction){
        if(direction.equals("away")){

            if(position<target.getPosition())
                position-=4;
            else if(position>target.getPosition())
                position+=4;
            else{
                int moveDirection;
                do{
                    System.out.println("\nChoose Direction:");
                    System.out.println("1. Left     2. Right");
                    System.out.print("Direction: ");
                    moveDirection=sc.nextInt();

                    switch(moveDirection){
                        case 1:
                            position-=4;
                            break;
                        case 2:
                            position+=4;
                            break;
                        default:
                            System.out.println("\n! Please Choose a valid direction to move into... !");
                            break;
                    }
                }while(moveDirection!=1 && moveDirection!=2);
                
            }
        }
        else if(direction.equals("close")){

            if(position<target.getPosition())
                position+=4;
            else if(position>target.getPosition())
                position-=4;
            else{
                int moveDirection;
                do{
                    System.out.println("\nChoose Direction to move:");
                    System.out.println("1. Left     2. Right");
                    System.out.print("Direction: ");
                    moveDirection=sc.nextInt();

                    switch(moveDirection){
                        case 1:
                            position-=4;
                            break;
                        case 2:
                            position+=4;
                            break;
                        default:
                            System.out.println("\n! Please Choose a valid direction to move into... !");
                            break;
                    }
                }while(moveDirection!=1 && moveDirection!=2);
                
            }
        }
    }

    public String getWeapon(){
        return weapon;
    }

    Enemy targetSelection(Enemy[] enemies) {
        while(true){
            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("Choose Target:");

            for(int i=0; i<enemies.length; i++){
                if(enemies[i].getHealth()>0){
                    System.out.print((i+1) + ". " + enemies[i].getName() + " [HP: " + String.format("%.1f", enemies[i].getHealth()) + "] [Distance: " + Math.abs(enemies[i].getPosition()-position) + "]");

                    if(enemies[i].isShielded())
                        System.out.print(" [Shielded]");

                    if(enemies[i].isStunned())
                        System.out.print(" [Stunned]");

                    System.out.println();
                }
                else{
                    System.out.println((i+1) + ". " + enemies[i].getName() + " [DEFEATED]");
                }
            }
            System.out.println();

            System.out.print("Target: ");
            int target=sc.nextInt();
            System.out.println();
            System.out.println("------------------------------------------");

            if(target>=1 && target<=enemies.length && enemies[target-1].getHealth()>0){
                return enemies[target-1];
            }

            System.out.println("Invalid target. Choose again.");
        }
    }

    void attackMenu(Fight fight){
        int attack;
        int apCost;

        do{
            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("1. Light Attack: " + attacks[0].getAttackName()
                    + " [Range: " + attacks[0].getAttackRange() + "] [AP Cost: 3]");
            System.out.println("2. Heavy Attack: " + attacks[1].getAttackName()
                    + " [Range: " + attacks[1].getAttackRange() + "] [AP Cost: 4]");
            System.out.println("3. Cast Spell: " + attacks[2].getAttackName()
                    + " [Range: " + attacks[2].getAttackRange() + "] [AP Cost: 3]");
            System.out.println("4. Ultimate Attack: " + attacks[3].getAttackName()
                    + " [Range: " + attacks[3].getAttackRange() + "] [AP Cost: 4]");
            System.out.println("------------------------------------------");

            System.out.print("\nChoose Attack: ");
            attack=sc.nextInt();

            if(attack<1 || attack>4){
                System.out.println("Invalid! Please choose a number between 1 and 4.");
                continue;
            }
        
            if(attack==1 || attack==3)
                apCost=3;
            else
                apCost=4;
        
            if(attack==4 && !isUltimateCharged()){
                System.out.println("Ultimate Attack is not charged! Kill 4 enemies to use an Ultimate Attack!");
                continue;
            }
        
            if(fight.getAP()<apCost){
                System.out.println("Not enough AP! You need " + apCost + " AP for this attack.");
                return;
            }
        
            break;
        
        }while(true);

        GameCharacter target;

        if(attack!=3){
            target=targetSelection(fight.getEnemyList());
        }
        else
            target=this;

        attacks[attack-1].performAttack(this, target);

        fight.decreaseAP(apCost);

        //Increase ultimate charge by 1 when killing an enemy.
        if(target instanceof Enemy && target.getHealth()<=0){
            increaseUltimateCharge();
            System.out.println(target.getName() + " has been vanquished!");
            System.out.println("Ultimate Charge: " + ultimateCharges + "/4");
        }

        //After using an ultimate, reset ultimate charge.
        if(attack==4){
            resetUltimateCharges();
            System.out.println("Ultimate Charge reset to 0.");
        }
    }

    public double getHealth(){
        return health;
    }

    public void adjustPoints(int rewardPoints){}

    public void chooseAction(Fight fight){
        int choice;

        do {
            System.out.println("AP: " + fight.getAP());
            System.out.println("1. Attack [AP Cost: 3-4]");
            System.out.println("2. Block [AP Cost: 4]");
            System.out.println("3. Move Away [AP Cost: 1]");
            System.out.println("4. Move Closer [AP Cost: 1]");
            System.out.print("Action: ");
            choice=sc.nextInt();

            if(choice<1 || choice>4){
                System.out.println("Invalid! Choose Again.");
                continue;
            }


            switch(choice){
                case 1:
                    attackMenu(fight);
                    if(fight.getAP()==0 || !fight.areEnemiesAlive())
                        return;
                    break;

                case 2:
                    if(fight.getAP() >= 4){
                        fight.decreaseAP(4);

                        if(Math.random()<0.80){
                            setShield(true);
                            System.out.println(getName() + " is now shielded!");
                        }
                        else
                            System.out.println("Block Attempt Failed!");

                        return;
                    }
                    System.out.println("Not enough AP! You need 4 AP to block.");
                    break;

                case 3:
                    if(fight.getAP() >= 1){
                        fight.decreaseAP(1);
                        Enemy target=targetSelection(fight.getEnemyList());
                        setPosition(target, "away");
                        System.out.println(getName() + " moved 4m away.");
                        return;
                    }
                    System.out.println("Not enough AP! You need 1 AP to move.");
                    break;

                case 4:
                     if(fight.getAP() >= 1){
                        fight.decreaseAP(1);
                        Enemy target=targetSelection(fight.getEnemyList());
                        setPosition(target, "close");
                        System.out.println(getName() + " moved 4m closer.");
                        return;
                    }
                    System.out.println("Not enough AP! You need 1 AP to move.");
                    break;

                default:
                    System.out.println("Invalid! Choose Again.");
                    chooseAction(fight);
            }
        }while(true);
    }

    public void adjustStats(int rewardPoints){
        while(rewardPoints>0){
            int choice;

            do{
                System.out.println("Choose which stat to increase.");
                System.out.println("1. Health");
                System.out.println("2. Strength");
                System.out.println("3. Speed");
                System.out.println("4. Endurance");
                System.out.print("Stat: ");
                choice=sc.nextInt();

                if(choice<1 || choice>4){
                    System.out.println("Invalid Choice! Please choose a correct stat to increase.");
                }

            }while(choice<1 || choice>4);

            int value;

            System.out.println("Remaining Reward Points: " + rewardPoints);
            System.out.print("How many points do you want to allocate to the stat?: ");
            value=sc.nextInt();

            if(value<1 || value>rewardPoints){
                System.out.println("Invalid Input! Please Try Again.");
            }
            else{
                switch(choice){
                    case 1:
                        setHealth(value);
                        break;

                    case 2:
                        increaseStrengthStat(value);
                        break;

                    case 3:
                        increaseSpeedStat(value);
                        break;

                    case 4:
                        increaseEnduranceStat(value);
                        break;
                }

                rewardPoints-=value;
            }
        }
    }
}