import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Random;

public class PlayingState implements State {
	private Model model;
	private Teki enemy;
	private LinkedList<Teki> enemys;

	String yourAnswer;

	//private Question question;

	int tekiflag = 0;//かぶって敵を同じ位置に出さないようにする。１はかぶってない。２はかぶってる
	int gunFlag = 2;//１がライフル、２がハンドガン
	private int drawflag = 0;//アリスの描画分け
	int tekitime = 40;
	private int timee = 0;
	private int time = 60;

	private Image zyuunomal;
	private Image zyu1;
	private Image zyu2;
	private Image zyu3;
	private Image zyu4;
	private Image haikei1;
	Image minihati;
	Image kira;

	private Image answer;
	Image waku;
	Image kami;
	Image monwaku;
	boolean ans = false;
	private int aristime = 20;
	int hit = 0;
	int kaifukuflag = 0;
	int sound = 0;//間違っていたらミスの音を流すのに使用
	int tyuui = 0;
	int tyuuitime = 4;
	private AudioClip battle;
	private AudioClip rifle;
	private AudioClip hand;
	private AudioClip kaifuku;
	private AudioClip miss;
	private AudioClip kirikae;
	private AudioClip tekikougeki;

	public PlayingState(Model model) {
		this.model = model;

		enemys = new LinkedList<Teki>();

		yourAnswer = "";

		// 画像を読み込む．画像ファイルは src においておくと bin に自動コピーされる
		haikei1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("haikei2.png"));
		zyuunomal = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyuunomal.png"));
		zyu1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyuuiti.png"));
		zyu2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyuuni.png"));
		zyu3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyuusan.png"));
		zyu4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("zyuuson.png"));
		monwaku = Toolkit.getDefaultToolkit().getImage(getClass().getResource("monhai.png"));
		kami = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kami1.png"));
		minihati = Toolkit.getDefaultToolkit().getImage(getClass().getResource("minihati.png"));
		kira = Toolkit.getDefaultToolkit().getImage(getClass().getResource("kiraa.png"));
		waku = Toolkit.getDefaultToolkit().getImage(getClass().getResource("waku.png"));
		answer = Toolkit.getDefaultToolkit().getImage(getClass().getResource("ans.png"));

		battle = Applet.newAudioClip(getClass().getResource("battle.wav"));
		rifle = Applet.newAudioClip(getClass().getResource("rifle.wav"));
		hand = Applet.newAudioClip(getClass().getResource("hand.wav"));
		kaifuku = Applet.newAudioClip(getClass().getResource("hp.wav"));
		miss = Applet.newAudioClip(getClass().getResource("miss.wav"));
		kirikae = Applet.newAudioClip(getClass().getResource("zyuchange.wav"));
		tekikougeki = Applet.newAudioClip(getClass().getResource("tekikita.wav"));
		battle.play();

	}

	public void processTimeElapsed(int msec) {
		tekitime++;
		timee++;
		aristime++;
		if (timee >= 10) {
			time--;

			timee = 0;
			tyuuitime++;//「銃のモードが違うよ」を書くときに使用
			model.time++;
		}
		model.changeState(gameOver());
		teki();

	}

	public State process(String event) {

		tyuui = 0;
		LinkedList<Teki> enemy = new LinkedList<Teki>();
		if (event.charAt(0) == ' ') {
			return new BossCome(this, model);
		} else if (event.charAt(0) == 'E') {//ansのTrue、Falseは同じ名前の敵をいっぺんに殺さないようにする

			for (Teki teki : enemys) {
				if (yourAnswer.equals(teki.getQuestion())) {//正解だったらenemyに追加しないで、間違ってたらする
					if ((gunFlag == 2 && teki.getDirection() <= 2) || (gunFlag == 1 && teki.getDirection() > 2)) {//銃モードが違う
						tyuui = 1;//注意フラグたつ
						tyuuitime = 0;
					}
					if ((teki.getQuestion().equals("kaifuku")) && gunFlag == 1) {

						if (!ans) {
							ans = true;

							//model.score++;
							drawflag = teki.getDirection();
							aristime = 0;
							sound = 1;
							rifle.play();
						} else
							enemy.add(teki);
					}

					else if ((gunFlag == 1 && teki.getDirection() <= 2) || (gunFlag == 2 && teki.getDirection() > 2)) {

						if (!ans) {
							ans = true;
							sound = 1;
							//model.score++;
							hit++;
							drawflag = teki.getDirection();
							aristime = 0;
							if (gunFlag == 2)
								hand.play();
							if (gunFlag == 1)
								rifle.play();
						} else
							enemy.add(teki);

					} else
						enemy.add(teki);

				} else {
					enemy.add(teki);

				}

			}
			ans = false;
			enemys = enemy;
			if (yourAnswer.equals("hand")) {
				gunFlag = 2;
				kirikae.play();
				sound = 1;
			}
			if (yourAnswer.equals("rifle")) {
				gunFlag = 1;
				kirikae.play();
				sound = 1;
			}
			yourAnswer = "";
			if (sound != 1)
				miss.play();
			sound = 0;
		}

		else if (event.charAt(0) == 'B') {//バックスペース押した。この処理しないとBossComから戻った時Answerに空欄が追加される

			if (yourAnswer.isEmpty()) {
				yourAnswer = "";
			} else {
				int nagasa = yourAnswer.length();
				String okikaeAto = yourAnswer.substring(0, nagasa - 1);
				yourAnswer = okikaeAto;
			}
		}

		else {
			if (yourAnswer.length() <= 10)
				yourAnswer += event;
			else {
			}
		}
		return this;

	}

	public void teki() {
		Random rand = new Random();
		int a = rand.nextInt(4) + 1;//0の分あるからプラス1する
		int b = rand.nextInt(7);

		//LinkedList<Teki> ene = new LinkedList<Teki>();
		if (tekitime >= 50) {
			if (enemys.size() <= 0)
				tekiflag = 1;
			else {
				for (Teki teki : enemys) {
					if (teki.getDirection() == a)
						tekiflag = 2;
					else {
						if (tekiflag != 2)
							tekiflag = 1;
					}
				}
			}
			if (tekiflag == 1) {
				tekitime = 0;
				enemys.add(new Teki(a, b));//( direction,  qnum)
				tekiflag = 0;
			} else if (tekiflag == 2)//もうすでにいるところに敵を出そうとしたら、出さない。
				tekiflag = 0;
		}

		LinkedList<Teki> ene = new LinkedList<Teki>();

		for (Teki teki : enemys) {
			if (teki.getTekiidou() >= 250) {
				if (teki.getQuestion().equals("kaifuku")) {
					kaifuku.play();
					if (model.hp <= 60)
						model.hp = 0;
					else
						model.hp -= 60;
					aristime = 0;
					drawflag = 0;
					kaifukuflag = 1;
				} else {
					tekikougeki.play();
					model.hp += 40;

					drawflag = 5;
					aristime = 0;
				}
			}

			else {
				ene.add(teki);
			}
		}
		enemys = ene;

	}

	public void paint(Graphics g) {

		// 描画する
		g.setFont(new Font(Font.SERIF, Font.TYPE1_FONT, 40));
		g.setColor(Color.WHITE);

		g.drawImage(haikei1, 0, 0, model.getView());

		if (drawflag == 5 && aristime > 2) {
			drawflag = 0;
		}
		if (aristime > 13) {
			drawflag = 0;
		}

		switch (drawflag) {
		case 1:
			g.drawImage(zyu1, 470, 270, model.getView());
			break;
		case 2:
			g.drawImage(zyu2, 470, 270, model.getView());
			break;
		case 3:
			g.drawImage(zyu3, 470, 270, model.getView());
			break;
		case 4:
			g.drawImage(zyu4, 470, 270, model.getView());
			break;
		case 5:
			break;
		default:
			g.drawImage(zyuunomal, 470, 270, model.getView());
			break;

		}

		g.drawImage(kami, 380, 60, model.getView());

		g.setColor(Color.RED);
		g.fillRect(428, 420, 250, 24);
		g.setColor(Color.BLUE);
		g.fillRect(428, 420, 250 - model.hp, 24);
		g.setColor(Color.WHITE);
		g.drawString("hp", 378, 440);
		g.setFont(new Font(Font.SERIF, Font.TYPE1_FONT, 30));
		g.drawString(250 - model.hp + "/250", 480, 445);

		//銃の選択
		Color color = new Color(87, 30, 20);
		g.setColor(color);
		g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		g.drawString("〜Gun Mode〜", 440, 125);
		g.setFont(new Font(Font.SERIF, Font.TYPE1_FONT, 40));
		if (hit < 11) {
			g.drawString("rifle", 480, 168);
			g.drawString("hand", 480, 210);
			g.setColor(Color.RED);
			if (gunFlag == 1) {
				g.drawString("▼", 420, 170);

				g.drawString("rifle", 480, 168);
			}
			if (gunFlag == 2) {
				g.drawString("▼", 420, 212);
				g.drawString("hand", 480, 210);
			}
		}
		g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
		g.setColor(Color.WHITE);

		if (hit == 11 && aristime < 30) {

			if (aristime == 0) {
				battle.stop();
				model.boss.play();
			}
			g.drawImage(waku, 80, 580, model.getView());
			g.drawString("よくも私の庭を荒らしたわねえ。", 170, 660);
			g.drawString("首をはねてやるわ！", 170, 703);
			g.setColor(Color.PINK);
			g.drawString("ハートの女王", 200, 620);
			drawflag = 0;
		} else if (hit == 11 && aristime >= 30) {
			hit++;
		} else
			g.drawImage(monwaku, 360, 554, model.getView());

		g.setFont(new Font(Font.SERIF, Font.TYPE1_FONT, 40));
		g.setColor(color);
		//スコア,time
		//  g.drawString( "Score"+model.score, 430, 580);
		//g.drawString("Time: " +time, 430,630);

		// 画像の表示例

		//g.drawImage(image, 100,100, model.getView());

		if (hit < 11) {
			for (Teki teki : enemys) {
				switch (teki.getDirection()) {
				case 1:
					g.drawImage(teki.getImage(), 150 + teki.getTekiidou(), 60 + teki.getTekiidou(), model.getView());
					g.drawImage(answer, 10, 10, model.getView());
					g.drawString(teki.getQuestion(), 53, 64);

					break;
				case 2:
					g.drawImage(teki.getImage(), 800 - teki.getTekiidou(), 60 + teki.getTekiidou(), model.getView());
					g.drawImage(answer, 790, 10, model.getView());
					g.drawString(teki.getQuestion(), 840, 64);

					break;
				case 3:
					g.drawImage(teki.getImage(), 100 + teki.getTekiidou(), 540 - teki.getTekiidou(), model.getView());
					g.drawImage(answer, 10, 680, model.getView());
					g.drawString(teki.getQuestion(), 70, 734);
					break;
				case 4:
					g.drawImage(teki.getImage(), 800 - teki.getTekiidou(), 540 - teki.getTekiidou(), model.getView());
					g.drawImage(answer, 790, 680, model.getView());
					g.drawString(teki.getQuestion(), 860, 734);
					break;
				default:
					break;
				}

				teki.update();
			}
		}
		g.setColor(color);
		g.drawString(yourAnswer, 400, 599);

		if (hit <= 10)
			g.drawImage(minihati, 360, 620, model.getView());
		if (hit <= 9)
			g.drawImage(minihati, 390, 620, model.getView());
		if (hit <= 8)
			g.drawImage(minihati, 420, 620, model.getView());
		if (hit <= 7)
			g.drawImage(minihati, 450, 620, model.getView());
		if (hit <= 6)
			g.drawImage(minihati, 480, 620, model.getView());
		if (hit <= 5)
			g.drawImage(minihati, 510, 620, model.getView());
		if (hit <= 4)
			g.drawImage(minihati, 540, 620, model.getView());
		if (hit <= 3)
			g.drawImage(minihati, 570, 620, model.getView());
		if (hit <= 2)
			g.drawImage(minihati, 600, 620, model.getView());
		if (hit <= 1)
			g.drawImage(minihati, 630, 620, model.getView());
		if (hit == 0)
			g.drawImage(minihati, 660, 620, model.getView());
		if (kaifukuflag == 1 && aristime < 10) {
			g.drawImage(kira, 650, 340, model.getView());
			g.drawString("heal!", 670, 370);

		} else {
			kaifukuflag = 0;
		}

		g.setColor(Color.RED);
		g.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 30));
		if (tyuui == 1 && tyuuitime < 2) {
			g.drawString("銃のモードが違うよ!", 390, 597);
		}

	}

	public State gameOver() {
		if (model.hp >= 250) {

			battle.stop();
			return new Badend(model);
		} else if (hit == 12) {

			return new Boss(model);

		} else
			return this;
	}

}
