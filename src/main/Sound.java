package main;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound(){
		
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure (1).wav");
		soundURL[1] = getClass().getResource("/sound/coin (1).wav");
		soundURL[2] = getClass().getResource("/sound/powerup (1).wav");
		soundURL[3] = getClass().getResource("/sound/unlock (1).wav");
		soundURL[4] = getClass().getResource("/sound/fanfare (1).wav");
	}
	public void setFile(int i){
		
		try{
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e){
			
		}
	}
	
	public void play(){
		
		clip.start();
	}
	
	public void loop(){
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop(){
		
		clip.stop();
	}
}
