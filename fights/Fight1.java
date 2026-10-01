package fights;
import characters.player.*;
import characters.enemies.*;

public class Fight1 extends Fight{
    
    public Fight1(){
        enemies=new Enemy[1];
        
        enemies[0]=(Knight) new Knight();
    }

}
