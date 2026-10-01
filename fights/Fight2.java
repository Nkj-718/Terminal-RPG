package fights;
import characters.player.*;
import characters.enemies.*;

public class Fight2 extends Fight{
    
    public Fight2(){
        enemies=new Enemy[1];
        
        enemies[0]=new Archer();
    }

}
