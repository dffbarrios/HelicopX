package classes;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Stage extends JFrame 
	implements ActionListener{	
    //private static final long serialVersionUID = 1L;
	
	private int level;
	private double totalScore;
	private Scene missionOne;
	private Scene missionTwo;
	private Scene missionThree;
	private Sounds missionComplete;	
	private boolean gameComplete;	
	private JPanel container;	
	
	private JMenuBar menuBar;
	private JMenu    menu;
	private JMenuItem closeGame;
	private JMenuItem restartGame;
	
	private String namePlayer;
	private int width;
	private int height;
	
	private void height(int value){
		height=value;
	}	
	private void width(int value){
		width=value;
	}	
	private int height(){
		return height;
	}
	private int width(){
		return width;
	}
	private void namePlayer(String value){
		namePlayer=value;
	}
	private String namePlayer(){
		return namePlayer;
	}
	
	public Stage(String imagePath, 
			String NAMEPLAYER,int WIDTH,
			int HEIGHT){		
		height(HEIGHT);
		width(WIDTH);
		namePlayer(NAMEPLAYER);
		
		Image icon=
				Toolkit.getDefaultToolkit().getImage(
						getClass().getResource(imagePath));
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setTitle("HelicopX v1.0");
		setIconImage(icon);	
		setSize(height,width);
		
		menuBar=new JMenuBar();		
		menu=new JMenu("Options");
		closeGame=new JMenuItem("Exit");		
		restartGame=new JMenuItem("Restart");
		
		menu.add(restartGame);
		menu.add(closeGame);
		menuBar.add(menu);
		setJMenuBar(menuBar);
				
		totalScore=0.0;
		gameComplete=false;
		
		container=new JPanel();
		container.setLayout(new BorderLayout(800,600));		
		
		missionOne=new Scene("/img/mission1.png","/img/helicopter.gif",
				"/img/redbullet.png","/img/explosion.png",
				totalScore,5,5,namePlayer,
				"You can play next mission!","Mission One Complete!",
				"/aud/helicopter.wav","/aud/explosion.wav",
				"/aud/gameover.wav","/aud/tada.wav");
		
		missionTwo=new Scene("/img/mission2.png","/img/helicopter.gif",
				"/img/redbullet.png","/img/explosion.png",
				totalScore,5,10,namePlayer,"You can play next mission!",
				"Mission two complete!","/aud/helicopter.wav",
				"/aud/explosion.wav","/aud/gameover.wav","/aud/tada.wav");
		
		missionThree=new Scene("/img/mission3.png","/img/helicopter.gif",
				"/img/redbullet.png","/img/explosion.png",
				totalScore,5,12,namePlayer,"Congratulations!",
				"Mission three complete!","/aud/helicopter.wav",
				"/aud/explosion.wav","/aud/gameover.wav","/aud/tada.wav");		
		
		missionComplete=new Sounds("/aud/final.wav",0);
		
		setLocationRelativeTo(null);
		setResizable(false);		
		
		closeGame.addActionListener(this);
		restartGame.addActionListener(this);
		addWindowListener(new gameEvents());
				
		container.add(missionOne,BorderLayout.CENTER);
		add(container);
		pack();
		setVisible(true);
	}
	
	private void formWindowOpened(java.awt.event.WindowEvent evt){
		JOptionPane.showMessageDialog(null,"This Game was developed by: @dfbarrios "
				+"\n Git Repository: https://github.com/diegoferbarrios/HelicopX.v1.0");
	}
	
	private void formWindowClosing(java.awt.event.WindowEvent evt){
		 int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Are you sure you want to exit HelicopX?", "Exit from HelicopX.v1.0",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_NO_OPTION) {
			      dispose();
			      System.exit(0);
			    }
	}	
	
	public class gameEvents extends WindowAdapter{
		
		public void windowOpened(java.awt.event.
				WindowEvent evt){
			formWindowOpened(evt);
		}
		
		public void windowClosing(java.awt.event.
				WindowEvent evt){
			formWindowClosing(evt);
		}
		
		public void windowActivated(
				java.awt.event.WindowEvent evt){
			checkMissionState();
		}
	}
	
	private void changeLevel(JPanel panel,
			Scene missionA, Scene missionB){
		panel.remove(missionA);
		panel.add(missionB, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
		this.pack();	
	}
	
	private void checkMissionState(){
		if(missionOne!=null)
			if(missionOne.complete()){
				changeLevel(container,missionOne,missionTwo);
				totalScore=missionOne.score();
				missionTwo.score(totalScore);
				missionOne=null;
			}
		
		if(missionTwo!=null)
			if(missionTwo.complete()){
				changeLevel(container,missionTwo,missionThree);
				totalScore=missionTwo.score();
				missionThree.score(totalScore);
				missionTwo=null;
			}
			
		if(missionThree!=null&&!gameComplete)
			if(missionThree.complete()){
				//container.remove(missionThree);
				//missionThree=null;				
				gameComplete=true;				
			}		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==closeGame){
			System.exit(0);
		}
		
		if(e.getSource()==restartGame){
			setVisible(false);
			//new Settings("/img/icon.png");
			//dispose();
		}		
	}	
}