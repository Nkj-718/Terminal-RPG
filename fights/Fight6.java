package fights;
import characters.player.*;
import characters.enemies.*;

public class Fight6 extends Fight{
    
    public Fight6(){
        enemies=new Enemy[3];
        
        enemies[0]=new Archer();
        enemies[1]=new Hound();
        enemies[2]=new Cleric();
    }

}
