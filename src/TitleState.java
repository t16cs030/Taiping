import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class TitleState implements State {
	private Model model;
	Image haikei;
	Image ansd;
	int time = 0;
	int timee = 0;
	private AudioClip title;

	public TitleState(Model model) {
		haikei = Toolkit.getDefaultToolkit().getImage(getClass().getResource("title.png"));
		ansd = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ansdd.png"));
		title = Applet.newAudioClip(getClass().getResource("title.WAV"));

		this.model = model;
		model.score = 0;
		model.i = 0;
		model.hp = 0;
		model.time = 0;
		title.play();

	}

	public void processTimeElapsed(int msec) {
		timee++;
		if (timee >= 7) {

			time++;
			timee = 0;
		}

	}

	public State process(String event) {
		if (event.charAt(0) == 'E') {

			title.stop();

			// return new RecordState(model);
			//		 return new Boss(model);
			return new kaiwa(model);

			//return new PlayingState(model);
		} else if (event.charAt(0) == ' ') {
			return new BossCome(this, model);
		}
		return this;
	}

	public void paint(Graphics g) {
		g.setFont(new Font(Font.SERIF, Font.ROMAN_BASELINE, 45));
		Color color = new Color(87, 30, 20);
		g.setColor(color);
		g.drawImage(haikei, -70, 0, model.getView());
		g.drawImage(ansd, 320, 605, model.getView());
		if (time % 2 == 0) {
			g.drawString("〜Push Enter〜", 455, 670);
		} else {

			g.drawString("〜Push Enter〜", 455, 660);

		}
		g.setFont(new Font(Font.SERIF, Font.BOLD, 21));
		g.drawString("3次元のボスが来たらスペースキーを押してね", 373, 694);
	}

}
