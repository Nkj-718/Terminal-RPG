package fights;
import characters.player.*;
import characters.enemies.*;

public class Fight4 extends Fight{
    
    public Fight4(){
        enemies=new Enemy[3];
        
        enemies[0]=new Archer();
        enemies[1]=new Cleric();
        enemies[2]=new Warden();
    }
}
