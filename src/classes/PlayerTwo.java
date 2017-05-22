package classes;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class PlayerTwo extends Player {

	public PlayerTwo(String path, int px, int py){
		super.setX(px);
		super.setY(py);
		super.setPath(path);
		ImageIcon h=new ImageIcon(this.getClass().getResource(getPath()));
		super.setImage(h.getImage());
	}

	@Override
	public void stopHelicopter(KeyEvent evt) {
		int key=evt.getKeyCode();
		switch(key){
		case KeyEvent.VK_W:
			super.mx=0; break;
		case KeyEvent.VK_D:
			super.mx=0; break;
		case KeyEvent.VK_S:
			super.my=0; break;
		case KeyEvent.VK_A:
			super.my=0; break;
		case KeyEvent.VK_G:
			super.mx=0; break;
		case KeyEvent.VK_T:
			super.my=0; break;
			}		
	}

	@Override
	public void normalActions(KeyEvent evt) {
		int key=evt.getKeyCode();
		switch(key){
		case KeyEvent.VK_A:
			super.mx=-2; break;
		case KeyEvent.VK_D:
			super.mx=2;	break;
		case KeyEvent.VK_W:
			super.my=-2; break;
		case KeyEvent.VK_S:
			super.my=2; break;
		}
	}

	@Override
	public void especialActions(KeyEvent evt) {
		int key=evt.getKeyCode();
		if(key==KeyEvent.VK_F)
			super.mx=10;
		if(key==KeyEvent.VK_R)
			super.my=-10;		
	}
}
