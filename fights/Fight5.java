package fights;
import characters.player.*;
import characters.enemies.*;

public class Fight5 extends Fight{
    
    public Fight5(){
        enemies=new Enemy[4];
        
        enemies[0]=new Archer();
        enemies[1]=new Cleric();
        enemies[2]=new Knight();
        enemies[3]=new Knight();
    }

}
