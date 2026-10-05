package fights;

import characters.enemies.*;

public class Fight7 extends Fight{
    
    public Fight7(){
        enemies=new Enemy[4];
        
        enemies[0]=new Warden();
        enemies[1]=new Cleric();
        enemies[2]=new Archer();
        enemies[3]=new Baron();
    }

}
