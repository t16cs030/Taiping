import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class RecordState implements State {
	private Model model;
	private Image end;
	private AudioClip godend;
	ArrayList<String> rank = new ArrayList<>();

	public RecordState(Model model) {
		godend = Applet.newAudioClip(getClass().getResource("godend.wav"));
		this.model = model;
		end = Toolkit.getDefaultToolkit().getImage(getClass().getResource("endga.png"));
		model.score = 1000 - 2 * model.hp - model.time;
		setRanking();
		Rank();
		godend.play();
	}

	public void processTimeElapsed(int msec) {

	}

	public State process(String event) {
		if (event.equals(" "))
			return new BossCome(this, model);
		if (event.charAt(0) == 'E') {
			godend.stop();
			return (State) new TitleState(model);
		}
		return this;
	}

	public void paint(Graphics g) {
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawImage(end, 0, 0, model.getView());
		int max = 0;
		if (rank.size() > 5)
			max = 5;
		else
			max = rank.size();
		for (int i = 0; i < max; i++) {
			g.drawString("" + rank.get(i), 950, 155 + i * 61);
		}
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		g.drawString("" + model.score, 654, 755);
	}

	public void setRanking() {
		String r = "" + model.getScore();
		try {
			File file = new File("ranking.txt");
			PrintWriter printwriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			printwriter.println(r);
			printwriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void Sort() {
		Collections.sort(rank, Collections.reverseOrder());
	}

	public void Rank() {
		File file = new File("ranking.txt");
		if (!file.exists()) {
			return;
		} else {
			try (FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);) {
				String data;
				while ((data = bufferedReader.readLine()) != null) {
					rank.add(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Sort();
	}

}