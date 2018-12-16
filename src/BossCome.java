import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BossCome implements State {
	private State state;
	private Model model;
	private Image boss;

	public BossCome(State state, Model model) {
		this.state = state;
		this.model = model;
	}

	public void processTimeElapsed(int msec) {
		boss = Toolkit.getDefaultToolkit().getImage(getClass().getResource("bosscome.png"));
	}

	public State process(String event) {
		if (event.equals(" "))
			return state;
		return this;
	}

	public void paint(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawImage(boss, 0, 0, model.getView());
	}
}

