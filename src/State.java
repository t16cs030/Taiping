import java.awt.Graphics;

public interface State {
	public void processTimeElapsed(int msec);
	public State process(String event);
	public void paint(Graphics g);
}
