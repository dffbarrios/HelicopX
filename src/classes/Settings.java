package classes;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Settings extends 
	JFrame implements ItemListener,
	ActionListener, KeyListener {
	
	private final int WIDTH=300;
	private final int HEIGHT=225;
	private final int LIMIT=8;
	
	private JLabel lblNamePlayer;
	private JLabel lblDimensions;	
	
	private JTextField jtfNamePlayer;
	private JComboBox cbxDimensions;
	
	private JButton btnPlay;
	private JButton btnInstructions;
	
	private Sounds sound;
	
	private Stage stage;
	private String iconPath;
	
	public Settings(String iconWindow){
		setLayout(null);
		
		iconPath=iconWindow;
		Image icon=Toolkit.getDefaultToolkit().
				getImage(getClass().getResource(iconPath));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Initial Settings");
		setIconImage(icon);
		setBounds(10, 10, WIDTH, HEIGHT);
		setBackground(Color.WHITE);		
		//setUndecorated(true);
				
		lblNamePlayer=new JLabel("Player Name");
		lblDimensions=new JLabel("Select Size");
		jtfNamePlayer=new JTextField();
		cbxDimensions=new JComboBox();
		btnPlay=new JButton("Lets go play!");
		btnInstructions=new JButton("Instrucctions");
		
		lblNamePlayer.setBounds(25, 0, 290, 30);
		jtfNamePlayer.setBounds(25, 30, 250, 30);
		lblDimensions.setBounds(25, 70, 100, 30);
		cbxDimensions.setBounds(25, 100, 250, 30);
		btnPlay.setBounds(155, 150, 120, 30);		
		btnInstructions.setBounds(25,150,120,30);
		
		cbxDimensions.addItem("  800x600 ");		
		
		//cbxDimensions.addItem(" 1000x750 ");
		jtfNamePlayer.setFont(new java.awt.Font
				("Candara",1,14));
		
		jtfNamePlayer.setHorizontalAlignment
			(JTextField.CENTER);		
		
		
		add(lblNamePlayer);
		add(jtfNamePlayer);				
		add(lblDimensions);
		add(cbxDimensions);		
		add(btnPlay);
		add(btnInstructions);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		
		btnPlay.setEnabled(false);
		jtfNamePlayer.setFocusable(true);		
		btnPlay.addActionListener(this);
		btnInstructions.addActionListener(this);		
		jtfNamePlayer.addKeyListener(this);		
		sound=new Sounds("/aud/intro.wav",0);
		sound.play();
	}
	
	public void keyTyped(KeyEvent e){
		if(jtfNamePlayer.getText().length()==LIMIT)
			e.consume();
		if(jtfNamePlayer.getText().length()>2)
			btnPlay.setEnabled(true);
		else
			btnPlay.setEnabled(false);
	}		
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnPlay){		
				if(cbxDimensions.getSelectedIndex()==0){
				stage=
					new Stage(iconPath,
							jtfNamePlayer.getText(),800,600);
				}				
			//sound.stop();
			//sound=null;
			dispose();
		}
		if(e.getSource()==btnInstructions){
			JOptionPane.showMessageDialog(null,
					"------------- Instructions -------------"+
					"\n" +"Directions       \n\n" +
					"RIGHT: Go ahead \n" +
					"LETF : Turn of Left \n"+
					"UP   : Go up \n" +
					"DOWN : Go down \n" +
					"\n"+
					"Especial Actions \n"+
					"L    : Turbo X \n"+
					"I    : Turbo Y \n"+
					"\n"+
					"Game Options	 \n"+
					"SPACE: Paused");
		}		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {}
	@Override
	public void keyPressed(KeyEvent arg0){}
	@Override
	public void keyReleased(KeyEvent arg0){}
}