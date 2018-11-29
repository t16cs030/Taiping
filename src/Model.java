
import java.applet.Applet;
import java.applet.AudioClip;

public class Model {
	private State state = new TitleState(this);
	
	
	
    private View view;
    private Controller controller;
	 AudioClip st;

 int score;
int i;//kaiwadesiyou


    private String typedChar = "";

    private int mx, my;
    int hp;
int time;

    AudioClip boss;

    public Model() {
        view = new View(this);
        controller = new Controller(this);
        i=0;
        hp=0;
        score=0;
        time=0;
        
        boss = Applet.newAudioClip(getClass().getResource("boss.wav"));
        st = Applet.newAudioClip(getClass().getResource("st.wav"));
      
        
    }

    public synchronized void processTimeElapsed(int msec) {
    	
    	state.processTimeElapsed(msec);
        view.repaint();
        
    }

    public synchronized void processKeyTyped(String typed) {
    	state = state.process(typed);
        view.repaint();
    }

    public synchronized void processMousePressed(int x, int y) {
        mx = x;
        my = y;
        //view.playBombSound();
        view.repaint();
    }

    public void start() {
        controller.start();
    }

    public View getView() {
        return view;
    }

    public Controller getController() {	
        return controller;
    }


    public String getTypedChar() {
        return typedChar;
    }

    public int getMX() {
        return mx;
    }

    public int getMY() {
        return my;
    }
    public void changeState(State s){
    	state = s;
    }
    public int getScore(){
    	return score;
    }

public State getState() { return state; }
}
