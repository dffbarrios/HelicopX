package classes;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Obstacle extends Objects {

	public final int xi=900;
	public final int min=50;
	public final int max=500;
	
	public Obstacle(String imagePath){
		
		super.setY(generatePy());		
		super.setX(xi);		
		super.setPath(imagePath);		
		ImageIcon e=new ImageIcon(
				this.getClass().getResource(
						super.getPath()));		
		super.setImage(e.getImage());
	}
	
	public int generatePy(){
		Random r=new Random();
		return (r.nextInt(max - min + 1) + min);
	}
	
	public void setMx(int v){
		super.x-=v;
	}
}


