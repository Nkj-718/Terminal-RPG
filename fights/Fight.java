package fights;

import characters.player.*;
import characters.enemies.*;
import characters.GameCharacter;
import java.util.Scanner;

public class Fight {
    private int rewardPoints;
    protected Enemy[] enemies;
    private int AP;

    Scanner sc=new Scanner(System.in);

    public void decreaseAP(int value){
        this.AP-=value;
    }

    public void increaseAP(int value){
        this.AP+=value;
    }

    public int getAP(){
        return AP;
    }

    public int getReward(){
        return rewardPoints;
    }

    public boolean areEnemiesAlive(){
        for(Enemy enemy : enemies){
            if(enemy.getHealth()>0)
                return true;
        }
        return false;
    }

    public Enemy[] getEnemyList(){
        return enemies;
    }

    private GameCharacter[] sortTurns(GameCharacter[] characters){
        for(int i=0; i<characters.length; i++){
            int max=i;

            for(int j=i+1; j<characters.length; j++){
                if(characters[j].getSpeed()>characters[max].getSpeed())
                    max=j;
            }

            GameCharacter temp=characters[i];
            characters[i]=characters[max];
            characters[max]=temp;
        }

        return characters;
    }

    private void displayBattleInfo(Player player){
        System.out.println();
        System.out.println("========== BATTLE STATUS ==========");

        System.out.println("PLAYER");
        System.out.println("Name: " + player.getName());
        System.out.println("Class: " + player.getClass().getSimpleName());
        System.out.println("Weapon: " + player.getWeapon());
        System.out.println("HP: " + String.format("%.1f", player.getHealth()));
        System.out.println("Strength: " + String.format("%.1f", player.getStrength()));
        System.out.println("Speed: " + String.format("%.1f", player.getSpeed()));
        System.out.println("Endurance: " + String.format("%.1f", player.getEndurance()));
        System.out.println("Critical Chance: " + String.format("%.1f", player.getCritChance() * 100) + "%");

        System.out.print("Status: ");
        boolean playerHasStatus=false;

        if(player.isShielded()){
            System.out.print("Shielded");
            playerHasStatus=true;
        }

        if(player.isStunned()){
            if(playerHasStatus)
                System.out.print(", ");
            System.out.print("Stunned");
            playerHasStatus=true;
        }

        if(!playerHasStatus)
            System.out.print("Normal");

        System.out.println();

        System.out.println();
        System.out.println("ENEMIES");

        for(int i=0; i<enemies.length; i++){
            Enemy enemy=enemies[i];

            if(enemy.getHealth()<=0){
                System.out.println((i+1) + ". " + enemy.getName() + " [DEFEATED]");
            }
            else{
                int distance=Math.abs(enemy.getPosition()-player.getPosition());

                System.out.print((i+1) + ". " + enemy.getName() + " [HP: " + String.format("%.1f", enemy.getHealth()) + "] [Distance: " + distance + "]");

                boolean enemyHasStatus=false;

                if(enemy.isShielded()){
                    System.out.print(" [Shielded]");
                    enemyHasStatus=true;
                }

                if(enemy.isStunned()){
                    System.out.print(" [Stunned]");
                    enemyHasStatus=true;
                }

                if(!enemyHasStatus)
                    System.out.print(" [Normal]");

                System.out.println();
            }
        }

        System.out.println("===================================");
        System.out.println();
    }

    void playTurn(Enemy[] enemies, Player player){
        GameCharacter[] characters=new GameCharacter[enemies.length+1];

        for(int i=0; i<enemies.length; i++){
            characters[i]=enemies[i];
        }

        characters[characters.length-1]=player;

        characters=sortTurns(characters);

        for(GameCharacter character : characters){
            //Perform Action
            if(character.getHealth()>0){

                if(!character.isStunned()){
                    if(character instanceof Cleric){
                        character.chooseAction(enemies, player);
                    }
                    //Handle Player Turn
                    else if(character instanceof Player){
                        increaseAP(4);
                        boolean endTurn=false;
                        displayBattleInfo(player);
                        System.out.println();
                        System.out.println("---------- YOUR TURN ----------");

                        if(getAP()>0){
                            do
                            {
                                System.out.println();
                                System.out.println("AP Remaining: " + AP);

                                player.chooseAction(this);

                                if(getAP()==0 || !areEnemiesAlive()){
                                    endTurn=true;
                                }
                                else{
                                    int choice;

                                    do{
                                        System.out.println("End Turn?");
                                        System.out.println("1. Yes    2. No");
                                        System.out.print("Choice: ");
                                        choice=sc.nextInt();

                                        if(choice<1 || choice>2){
                                            System.out.println("Invalid! Please choose the correct option.");
                                        }

                                    }while(choice<1 || choice>2);

                                    if(choice==1)
                                        endTurn=true;
                                }

                            }while(!endTurn);
                        }
                    }
                    else{
                        character.chooseAction(player);
                    }
                }
                else{
                    System.out.println(character.getName() + " is stunned and cannot act!");
                    character.setStun(false);
                }

            }

            //If player Character dies, end turn.
            if(player.getHealth()<=0){
                System.out.println("You Died.");
                return;
            }

            //If enemies die, end turn.
            if(!areEnemiesAlive()){
                System.out.println("Enemies Vanquished! You Win.");
                return;
            }
        }
    }

    public boolean startFight(Player player){
        boolean levelFinished=false;
        AP=0;

        System.out.println();
        System.out.println("========== BATTLE START ==========");
        System.out.println("You are facing:");

        for(int i=0; i<enemies.length; i++){
            System.out.println((i+1) + ". " + enemies[i].getName());
        }

        System.out.println("==================================");
        System.out.println();

        do{
            playTurn(enemies, player);
        }while(player.getHealth()>0 && areEnemiesAlive());

        if(player.getHealth()>0)
            levelFinished=true;

        rewardPoints=0;

        for(Enemy enemy : enemies){
            if(enemy.getHealth()<=0){
                rewardPoints+=enemy.giveReward();
            }
        }

        return levelFinished;
    }
}