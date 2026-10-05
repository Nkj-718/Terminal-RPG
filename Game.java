import characters.player.*;
import fights.*;
import story.*;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int rewardPoints=0;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String playerName = sc.next();
        int classSelect;

        do {
            System.out.println("Select your Character Class: ");
            System.out.println("1. Rogue");
            System.out.println("2. Barbarian");
            System.out.println("3. Duelist");
            System.out.println("4. Swordsman");
            System.out.println();
            System.out.print("Character: ");
            classSelect = sc.nextInt();

            if (classSelect < 1 || classSelect > 4)
                System.out.println("Invalid Class Selection! Try Again.");

        } while (classSelect < 1 || classSelect > 4);

        Player player = new Player();
        switch (classSelect) {
            case 1:
                player = new Rogue();
                break;
            case 2:
                player = new Barbarian();
                break;
            case 3:
                player = new Duelist();
                break;
            case 4:
                player = new Swordsman();
                break;
        }
        player.setName(playerName);

        Story[] chapters = new Story[9];
        chapters[0] = new Chapter1();
        chapters[1] = new Chapter2();
        chapters[2] = new Chapter3();
        chapters[3] = new Chapter4();
        chapters[4] = new Chapter5();
        chapters[5] = new Chapter6();
        chapters[6] = new Chapter7();
        chapters[7] = new Chapter8();
        chapters[8] = new Chapter9();

        Fight[] fights = new Fight[8];
        fights[0] = new Fight1();
        fights[1] = new Fight2();
        fights[2] = new Fight3();
        fights[3] = new Fight4();
        fights[4] = new Fight5();
        fights[5] = new Fight6();
        fights[6] = new Fight7();
        fights[7] = new Fight8();

        for (int i = 0; i < fights.length; i++) {
            System.out.println("-------- Chapter " + (i + 1) + " --------");
            chapters[i].startStory(player, sc);

            player.savePlayer();

            boolean levelFinish = fights[i].startFight(player);

            player.resetPlayer();

            rewardPoints+=fights[i].getReward();

            if (!levelFinish) {
                System.out.println("Attempt Failed! Booting up the level again...");

                switch (i) {
                    case 0:
                        fights[i] = new Fight1();
                        break;
                    case 1:
                        fights[i] = new Fight2();
                        break;
                    case 2:
                        fights[i] = new Fight3();
                        break;
                    case 3:
                        fights[i] = new Fight4();
                        break;
                    case 4:
                        fights[i] = new Fight5();
                        break;
                    case 5:
                        fights[i] = new Fight6();
                        break;
                    case 6:
                        fights[i] = new Fight7();
                        break;
                    case 7:
                        fights[i] = new Fight8();
                        break;
                }

                i--;
            } else {
                System.out.println("-------- Chapter " + (i + 1) + " completed! --------");
            }
            
            System.out.println("-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x--x-x-x-x-x-x-x-x-");
            System.out.println("Do you want to use the rewarded points to adjust your stats?");
            System.out.println("1.Yes");
            System.out.print("Adjust Points?: ");
            int choice=sc.nextInt();
            if(choice==1){
                player.adjustStats(rewardPoints);
                rewardPoints=0;
            }
        }

        System.out.println("-------- Chapter 9 --------");
        chapters[8].startStory(player, sc);
        System.out.println("-------- Chapter 9 completed! --------");
        System.out.println("-x-x-x-x-x-x-x-x- THANK YOU FOR PLAYING! -x-x-x-x-x-x-x-x-");

        sc.close();
    }
}
