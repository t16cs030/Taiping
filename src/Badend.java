import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Badend implements State  {
	private Model model;
	private Image badend;
	private String sora[]= new String[5];
	private AudioClip bad;
	public Badend(Model model){
		this.model=model;
		badend = Toolkit.getDefaultToolkit().getImage(getClass().getResource("gameover.png"));
		 bad= Applet.newAudioClip(getClass().getResource("badend.wav"));
		 bad.play();}
	
	public void processTimeElapsed(int msec){
		
	}

	public State process(String event) {
		if (event.equals(" "))
			return new BossCome(this,model);
		else if (event.charAt(0) == 'E'){
			model.i=0;
			bad.stop();
			return (State) new TitleState(model);}
		return this;
		}
	public void paint(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
       g.setColor(Color.WHITE);
       g.drawImage(  badend, 0, 0,model.getView());
		
		}
}