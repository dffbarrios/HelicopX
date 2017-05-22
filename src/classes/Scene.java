package classes;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Scene extends JPanel implements ActionListener,KeyListener {
		//Constants
		private final int WIDTH=800;
		private final int HEIGHT=600;		
		//states
		public boolean paused;
		public boolean started;
		public boolean gameOver;
		public boolean complete;		
		//Controls
		private double score;
		private int numberBullets;
		private int numberShot;
		private int speedBullet;
		private String namePlayer;
		private String messageWinner;
		private String messageTitle;		
		//Scene Objects
		private Efect explosion;
		private Background background;
		private PlayerOne helicopter;
		private Obstacle[] bullets;		
		//Sounds
		private Timer timer;	
		private Sounds audioHelicopter;
		private Sounds audioBullets;
		private Sounds audioExplosion;
		private Sounds audioGameOver;
		private Sounds audioWinner;		
		
		//Colission Control
		private Rectangle areaPlayer;
		private Rectangle areaBullet;	
		
		//Dimensions of images
		private int heightHelicopter;
		private int widthHelicopter;
		private int heightBullets;
		private int widthBullets;	
		
		
		
		public Scene(String imageBackground,String imagePlayer,	String imageObstacle, String imageExplosion, 
				double scoring, int numObstacles, int speed, String namePlayer, String messageWinner, String messageTitle,
				String soundPlayer, String soundExplosion, String soundGameOver, String soundWinner){
			
			inizialitingVariables(scoring,numObstacles,speed,namePlayer,messageWinner,messageTitle);
			createObjects(imageBackground,imagePlayer,imageExplosion);
			createObstacles(imageObstacle,numObstacles);
			createSounds(soundPlayer,soundExplosion,soundGameOver,soundWinner);
			getObjectsSize();
					
			setFocusable(true);		
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			timer=new Timer(10,this);
			timer.start();				
			addKeyListener(new Controls());
		}
		
	public void paint(Graphics graphic){
		super.paint(graphic);
		Graphics2D g=(Graphics2D) graphic;
					
		drawBackground(graphic);	
		if(!gameOver){
			drawPlayer(graphic);
		}
		
		
		if(numberShot<bullets.length
				&&started&&!paused&&!gameOver){
			drawBullet(graphic);
			moveBullet(graphic);
			checkColission(graphic);
			verifyBulletPosition();
		}
		
		if(numberShot>=bullets.length&&!complete){
			complete(true);
			audioHelicopter.stop();
			audioWinner.play();
			JOptionPane.showMessageDialog(null,"Winner!");
			setFocusable(false);
		}
		
		if(gameOver){
			drawGameOver(graphic);
		}
		
		drawMessages(graphic);
	}	

	public void actionPerformed(ActionEvent e){
		if(!gameOver&&!complete){
			score+=0.1;
			helicopter.moveHelicopter();
			repaint();
		}
	}
		
		public void checkColission(Graphics g){
			areaPlayer=new 
					Rectangle(helicopter.x,helicopter.y,
							widthHelicopter,heightHelicopter);
			areaBullet=new 
					Rectangle(bullets[numberShot].x,
							bullets[numberShot].y,
							widthBullets,heightBullets);
			
			g.fillRect(areaPlayer.x, areaPlayer.y, 
					areaPlayer.width, areaPlayer.height);
			
			g.fillRect(areaBullet.x, areaBullet.y, 
					areaBullet.width, areaBullet.height);
			
			
			if(areaBullet.intersects(areaPlayer)){
				gameOver(true);			
				audioHelicopter.stop();
				audioExplosion.play();
				audioGameOver.play();
				//drawExplosion(g);
			}	
		}
		
	public void drawMessages(Graphics g){	
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",1,45));		
		if(!started){
			g.drawString("Press ENTER to START",
					(int)(WIDTH-(WIDTH*0.80)),
					(int)(HEIGHT/2));
		}else if(gameOver){				
			g.setColor(Color.RED);
			g.drawString("Game Over",
					(int)(WIDTH-(WIDTH*0.7)),
					(int)(HEIGHT/10));		
			g.drawString("Score: "+(int)score+" p",
					(int)(WIDTH-(WIDTH*0.7)),
					(int)(HEIGHT-(HEIGHT*0.8)));			
		}else if(paused&&started){
			g.drawString("PAUSE",
					(int)(WIDTH-(WIDTH*0.6)),
					(int)(HEIGHT/2));			
		}else if(started&&!gameOver&&!complete){			
			g.setFont(new Font("Arial",1,15));
			g.drawString("Score: "+ (int)(score)+" p",
					15,20);				
		}else if(complete){
			g.drawString("WINNER!",(int)(WIDTH-(WIDTH*0.7)),
					(int)(HEIGHT/10));
			g.drawString("Score: "+(int)score+" p",
					(int)(WIDTH-(WIDTH*0.7)),
					(int)(HEIGHT-(HEIGHT*0.8)));
		}
	}	
		
		public void inizialitingVariables(double scoring,int nb,
				int sb, String np,
				String message, String title){
			messageTitle=title;
			messageWinner=message;
			started(false);
			gameOver(false);
			paused(false);
			complete(false);
			score(scoring);
			numberBullets(nb);
			numberShot(0);
			speedBullet(sb);
			namePlayer(np);
			nb=0;
		}	
		
		public void createObjects(String imageBackground,String imagePlayer, 
				String imageExplosion){
			background=new Background(imageBackground,0,0);
			helicopter=new PlayerOne(imagePlayer,10,(HEIGHT/2)-50);
			explosion=new Efect(imageExplosion,0,0);		
		}	
		
		public void createSounds(String soundPlayer, String soundExplosion, 
				String soundGameOver, String soundWinner){
			audioHelicopter=new Sounds(soundPlayer,1);
			audioExplosion=new Sounds(soundExplosion,0);
			audioGameOver=new Sounds(soundGameOver,0);
			audioWinner=new Sounds(soundWinner,0);
		}			
		
		public void createObstacles(String imageObstacle, int numObstacles){
			bullets=new Obstacle[numObstacles];
			for(int i=0;i<bullets.length;i++){
				bullets[i]=new Obstacle(imageObstacle);
			}
		}		
		
		public void getObjectsSize(){
			ImageIcon h=new ImageIcon(helicopter.getImage());
			ImageIcon b=new ImageIcon(bullets[numberShot].getImage());
			heightHelicopter=h.getIconHeight();
			widthHelicopter=h.getIconWidth();
			heightBullets=b.getIconHeight();
			widthBullets=b.getIconWidth();	
		}
				
		public class Controls extends KeyAdapter{
			public void keyReleased(KeyEvent e){
					if(started)
						helicopter.keyRealease(e);		
				}			
			public void keyPressed(KeyEvent e){
				if(!gameOver){
					if(started&&!paused)
						helicopter.keyPressed(e);
					if(e.getKeyCode()==e.VK_ENTER&&!started){
						started(true);
						audioHelicopter.play();
					}				
					if(e.getKeyCode()==e.VK_SPACE){
						if(paused) 
							audioHelicopter.play();
							else 
								audioHelicopter.stop();
						paused(!paused);
					}
				}
				
			}
		}
		
		public void drawGameOver(Graphics g){
			g.drawImage(explosion.getImage(),WIDTH/8,HEIGHT/8,this);
		}
		
		public void drawBackground(Graphics g){
			g.drawImage(background.getImage(),background.getX(),background.getY(),this);
		}
		
		public void drawPlayer(Graphics g){
			g.drawString(namePlayer,helicopter.getX()+50,helicopter.getY()-10);
			g.drawImage(helicopter.getImage(),helicopter.getX(),helicopter.getY(),this);
		}
		
		public void drawBullet(Graphics g){
			g.drawImage(bullets[numberShot].getImage(),
					bullets[numberShot].getX(),bullets[numberShot].getY(),this);
		}
		
		public void moveBullet(Graphics g){
			bullets[numberShot].setMx(speedBullet);	
		}
		
		public void drawExplosion(Graphics g){ 
			g.drawImage(explosion.getImage(),helicopter.x,helicopter.y,this);
		}
		
		public void verifyBulletPosition(){
			if(bullets[numberShot].getX()<-100){				
				numberShot++;				
				bullets[numberShot-1]=null;
			}
		}
		//Encapsulation Booleans
		public void paused(boolean value){
			paused=value;
		}
		public void started(boolean value){
			started=value;
		}
		public void gameOver(boolean value){
			gameOver=value;
		}
		public void complete(boolean value){
			complete=value;
		}
		
		//Encapsulation Controls
		public void score(double value){
			score=value;
		}	
		public void numberBullets(int value){
			numberBullets=value;
		}
		public void speedBullet(int value){
			speedBullet=value;
		}
		public void namePlayer(String value){
			namePlayer=value;
		}
		private void numberShot(int value){
			numberShot=value;
		}

		//Encapsulations (getters) Booleans
		public boolean paused(){
			return paused;
		}
		public boolean started(){
			return started;
		}
		public boolean gameOver(){
			return gameOver;
		}
		public boolean complete(){
			return complete;
		}
		
		//Encapsulation (setters) Booleans
		public double score(){
			return score;
		}
		public int numberBullets(){
			return numberBullets;
		}
		public int speedBullet(){
			return speedBullet;
		}
		public int numberShot(){
			return numberShot;
		}
		public String namePlayer(){
			return namePlayer;
		}
		@Override
		public void keyPressed(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyTyped(KeyEvent e) {}	
}