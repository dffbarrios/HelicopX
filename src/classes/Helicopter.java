package classes;
import java.awt.event.KeyEvent;

public interface Helicopter {
	boolean checkLimits();	
	void moveHelicopter();
	void stopHelicopter(KeyEvent evt);
	void normalActions(KeyEvent evt);
	void especialActions(KeyEvent evt);		
	void keyPressed(KeyEvent evt);
	void keyRealease(KeyEvent evt);
}


