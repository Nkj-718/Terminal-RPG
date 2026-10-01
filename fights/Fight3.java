package fights;
import characters.player.*;
import characters.enemies.*;

public class Fight3 extends Fight{
    
    public Fight3(){
        enemies=new Enemy[2];
        
        enemies[0]=new Knight();
        enemies[1]=new Cleric();
    }

}
