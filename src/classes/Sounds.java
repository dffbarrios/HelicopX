package classes;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Sounds {	
	
	private Clip sound;	
	private URL urlSound;
	private AudioInputStream inputStream;
	
	private boolean looping;
	private String  fileName;
	private int     repeat;
	
	
	public Sounds(){
		setFileName("");
		//fileName="";
		try{
			sound=AudioSystem.getClip();
		}catch(Exception e){
			JOptionPane.
				showMessageDialog(null,
				"Error Playing Audio. "
				+ "Closing The Game.");
			System.exit(0);
		}
	}
	
	public Sounds(String filename,int r){
		this();
		repeat=r;
		load(filename);
	}
	public void setLooping(boolean l){
		looping=l;
	}
	public void setRepeat(int r){
		repeat=r;
	}
	public void setFileName(String fn){
		fileName=fn;
	}
	public boolean getLooping(){
		return looping;
	}
	public int getRepeat(){
		return repeat;
	}
	public String getFileName(){
		return fileName;
	}
	public Clip getSound(){
		return sound;
	}
	
	private URL getURL(String fn){
		URL url=null;
		try{
			url=this.getClass().getResource(fn);
		}catch(Exception e){
			JOptionPane.
			showMessageDialog(null,
					"Error Playing Sound: "+e.toString());
		}
		return url;
	}
	
	public boolean isLoaded(){
		return (boolean)(sound!=null);	}
	
	public boolean load(String audioFile){
		try{
			setFileName(audioFile);
			inputStream=AudioSystem.
					getAudioInputStream(getURL(fileName));
			sound.open(inputStream);
			return true;
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,
					"Error Playing Sound: "+e.toString());
			return false;
		}catch(UnsupportedAudioFileException e){
			JOptionPane.showMessageDialog(null,
					"Error Playing Sound: "+e.toString());
			return false;
		}catch(LineUnavailableException e){
			JOptionPane.showMessageDialog(null,
					"Error Playing Sound: "+e.toString());
			return false;
		}
	}
	public void play(){
		if(!isLoaded())
			return;		
		sound.setFramePosition(0);
		if(repeat!=0)
			sound.loop(sound.LOOP_CONTINUOUSLY);
		else if(repeat==0)
			sound.start();
	}	
	public void stop(){
		sound.stop();
	}
}