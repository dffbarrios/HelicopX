package classes;
import java.awt.Image;
import java.awt.event.KeyEvent;

public  abstract class Player extends
	Objects implements Helicopter {
	
	protected final int lIMITUP=-50;
	protected final int LIMITDOWN=600;
	protected final int LIMITRIGHT=-150;
	protected final int LIMITLEFT=750;	
	
	protected int mx,my;
	
	public void setMx(int value){
		mx=value;}
	
	public void setMy(int value){
		my=value;}
	
	public int getMx(){
		return mx;}
	
	public int getMy(){
		return my;}
	
	public Image getImage(){
		return super.getImage();
	}
		
	public void moveHelicopter(){
		super.x+=this.mx;
		super.y+=this.my;
	}
	
	public boolean checkLimits(){		
		if(((getY()<lIMITUP)
				||getY()>LIMITDOWN)
				||((getX()>LIMITLEFT)
				||(getX()<LIMITRIGHT))){	
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		if(checkLimits()){
			normalActions(evt);	
			especialActions(evt);
		}else{
			super.x=30;
			super.y=150;
		}		
	}
	
	@Override
	public void keyRealease(KeyEvent evt) {
		stopHelicopter(evt);		
	}
}
