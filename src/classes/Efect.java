package classes;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Efect extends Objects {	
	
	public Efect(String urlImage, 
			int px, int py){
		
		super.setY(px);
		super.setX(py);
		super.setPath(urlImage);
		
		ImageIcon e=new ImageIcon(
				this.getClass().
				getResource(super.getPath()));
		
		super.setImage(e.getImage());
	}	
}

