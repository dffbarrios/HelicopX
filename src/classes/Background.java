package classes;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class Background extends Objects {	
	
		public Background(String urlImage,
				int px, int py){
			super.setX(px);
			super.setY(py);
			super.setPath(urlImage);
			
			ImageIcon b=new ImageIcon(
					this.getClass().
						getResource(super.getPath()));
			
			super.setImage(b.getImage());
		}	
}

