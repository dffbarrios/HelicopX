package classes;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class PlayerOne extends Player {
	
	public PlayerOne(String path, int px, int py){
		super.setX(px);
		super.setY(py);
		super.setPath(path);
		ImageIcon h=new ImageIcon(this.getClass()
				.getResource(getPath()));
		super.setImage(h.getImage());
	}

	@Override
	public void stopHelicopter(KeyEvent evt) {
		int key=evt.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			super.mx=0; break;
		case KeyEvent.VK_RIGHT:
			super.mx=0; break;
		case KeyEvent.VK_UP:
			super.my=0; break;
		case KeyEvent.VK_DOWN:
			super.my=0; break;
		case KeyEvent.VK_I:
			super.my=0; break;
		case KeyEvent.VK_L:
			super.mx=0; break;
			}		
	}

	@Override
	public void normalActions(KeyEvent evt) {
		int key=evt.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			super.mx=-2; break;
		case KeyEvent.VK_RIGHT:
			super.mx=2;	break;
		case KeyEvent.VK_UP:
			super.my=-2; break;
		case KeyEvent.VK_DOWN:
			super.my=2; break;
		}
	}

	@Override
	public void especialActions(KeyEvent evt) {
		int key=evt.getKeyCode();
		if(key==KeyEvent.VK_L)
			super.mx=10;
		if(key==KeyEvent.VK_I)
			super.my=-10;		
	}

}
