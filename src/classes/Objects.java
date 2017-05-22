package classes;
import java.awt.Image;

public class Objects {
	
	protected String path;
	protected Image image;
	protected int x,y;
	
	public void setPath(String value){
		path=value;	}
	
	public String getPath(){
		return path;
		}
	
	public void setImage(Image value){
		image=value;
		}
	
	public Image getImage(){
		return image;
		}
	
	public void setX(int value){
		x=value;
		}
	
	public int getX(){ 
		return x; 
	}
	
	public void setY(int value){
		y=value;
		}	
	
	public int getY(){
		return y;
		}
	
	public Objects(){
		setY(0);
		setX(0);
		setPath("");
	}
}
